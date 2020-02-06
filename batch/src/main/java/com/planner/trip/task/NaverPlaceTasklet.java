package com.planner.trip.task;

import com.planner.trip.model.*;
import com.planner.trip.payload.LocalData;
import com.planner.trip.payload.items;
import com.planner.trip.repository.AddressRepo;
import com.planner.trip.repository.ApiHistoryRepo;
import com.planner.trip.repository.NaverPlaceHistoryRepo;
import com.planner.trip.repository.RestaurantRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
public class NaverPlaceTasklet implements Tasklet {
    @Autowired
    private ApiHistoryRepo apiHistoryRepo;
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private NaverPlaceHistoryRepo naverPlaceHistoryRepo;
    @Autowired
    private RestaurantRepo restaurantRepo;
    private final String apiName="naver_place";
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        LocalDateTime startDatetime
                = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));
        LocalDateTime endDatetime
                = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
        //API HISTORY에서 요청 횟수조회
        Optional<ApiHistory> optHistory = apiHistoryRepo.findByApiNameAndWorkedAtBetween(apiName,startDatetime,endDatetime);
        ApiHistory history;
        if(optHistory.isPresent() ){
            history = optHistory.get();
            if(history.getRequestCount() >=23000){ // 요청제한
                return RepeatStatus.FINISHED;
            }
        }else{
            history = ApiHistory.builder()
                    .apiName("naver_place")
                    .requestCount(0)
                    .build();
            apiHistoryRepo.save(history);
        }
        Optional<NaverPlaceHistory> optPlaceHistory = naverPlaceHistoryRepo.findLastJob();
        NaverPlaceHistory naverPlaceHistory;
        Long startIdx;
        if(optPlaceHistory.isPresent()){
            naverPlaceHistory = optPlaceHistory.get();
        }else{
            naverPlaceHistory = NaverPlaceHistory.builder()
                    .addrIdx(2L).keyword("맛집")
                    .stored(0).total(0).build();
            naverPlaceHistoryRepo.save(naverPlaceHistory);
        }

        startIdx = getStartIdx(naverPlaceHistory);
        int rank= 31;//TODO 기존 데이터 있을시 수정필요
        Long end = addressRepo.findLastIdx();
        while (true){

            if(history.getRequestCount() >=15000  || end == startIdx){
                apiHistoryRepo.save(history);
                break;
            }

            Optional<Address> optionalAddress = addressRepo.findById(startIdx);
            if(!optionalAddress.isPresent()){
                startIdx++;
                continue;
            }
            Address address = optionalAddress.get();

            String query = address.getSido()+" "+address.getSigungu()+" "+address.getDong()+" 맛집";
            log.info("query : " + query);
            int startNum = naverPlaceHistory.getStored()+1;
            log.info("startNum : " + startNum);

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Naver-Client-Id","TISrvr5iEQlBphdCKCVu");
            headers.add("X-Naver-Client-Secret","V6_nQtjDxM");
            HttpEntity httpEntity = new HttpEntity(headers);
            URI uri = UriComponentsBuilder
                    .fromHttpUrl("https://openapi.naver.com/v1/search/local.json")
                    .queryParam("query",query).encode()
                    .queryParam("display","30")
                    .queryParam("start",startNum)
                    .queryParam("sort","comment")
                    .build()
                    .toUri();
            ResponseEntity<LocalData> tt=null;
            try {
                 tt = restTemplate.exchange(uri, HttpMethod.GET,httpEntity,LocalData.class);
            }catch (HttpClientErrorException e){
                log.error(e.getMessage());
                naverPlaceHistory = NaverPlaceHistory.builder()
                        .addrIdx(naverPlaceHistory.getAddrIdx()+1).keyword("맛집")
                        .stored(0).total(0).build();
                startIdx= naverPlaceHistory.getAddrIdx();
                rank=1;
                continue;
            }
            if(tt.getStatusCode() != HttpStatus.OK){
                log.info(tt.getStatusCode().name());
                apiHistoryRepo.save(history);
                break;
            }

            LocalData dd= tt.getBody();

            for(items items: dd.getItems()){
                String link = items.getLink();
                if(link.length()>254){
                    link = "";
                }
                if(link.indexOf("https://www.facebook.com/profile.php?id=1000062116")!=-1){
                    log.debug("https://www.facebook.com/profile.php?id=1000062116");
                    link="";
                }
                restaurantRepo.save(
                    Restaurant.builder()
                            .title(items.getTitle())
                            .addrIdx(startIdx)
                            .address(items.getAddress())
                            .category(items.getCategory())
                            .Latitude(Long.parseLong(items.getMapy()))
                            .link(link)
                            .longitude(Long.parseLong(items.getMapx()))
                            .rank(rank++)
                            .roadAddress(items.getRoadAddress())
                            .telephone(items.getTelephone())
                            .build()
                );
            }

            naverPlaceHistory.setTotal(dd.getTotal());
            naverPlaceHistory.setStored(rank-1);
            naverPlaceHistory.setDistrict(query);

            naverPlaceHistoryRepo.save(naverPlaceHistory);
            history.setRequestCount(history.getRequestCount()+1);
            if( naverPlaceHistory.getTotal()<=naverPlaceHistory.getStored()){
                naverPlaceHistory = NaverPlaceHistory.builder()
                        .addrIdx(naverPlaceHistory.getAddrIdx()+1).keyword("맛집")
                        .stored(0).total(0).build();
                startIdx= naverPlaceHistory.getAddrIdx();
                rank=1;
            }
        }

        return RepeatStatus.FINISHED;
    }

    private Long getStartIdx(NaverPlaceHistory naverPlaceHistory){
        if(naverPlaceHistory.getStored()==0){
            return naverPlaceHistory.getAddrIdx();
        }
        if( naverPlaceHistory.getTotal()<=naverPlaceHistory.getStored()){
            return naverPlaceHistory.getAddrIdx() +1;
        }else{
            return naverPlaceHistory.getAddrIdx();
        }
    }
}
