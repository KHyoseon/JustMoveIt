package com.ssafy.CommonPJT.dto.Ticket;

import com.ssafy.CommonPJT.domain.Classification;
import com.ssafy.CommonPJT.domain.MoviePlayingInfo;
import com.ssafy.CommonPJT.domain.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TicketSaveDto {

    private String phoneNumber;
    private String seat;
    private Classification classification;

    private Long moviePlayingInfoId;

    private LocalDateTime reservationTime;

    @Builder
    public Ticket toEntity(MoviePlayingInfo moviePlayingInfo) {
        return Ticket.builder()
                .phoneNumber(this.phoneNumber)
                .seat(this.seat)
                .moviePlayingInfo(moviePlayingInfo)
                .classification(this.classification)
                .reservationTime(this.reservationTime)
                .build();
    }
}
