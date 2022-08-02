package com.ssafy.CommonPJT.dto.req;

import com.ssafy.CommonPJT.domain.Classification;
import com.ssafy.CommonPJT.domain.MoviePlayingInfo;
import com.ssafy.CommonPJT.domain.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private String phoneNumber;

    private String seat;
    private Classification classification;
    @Builder
    public Ticket toEntity(MoviePlayingInfo moviePlayingInfo) {
        return Ticket.builder()
                .phoneNumber(this.phoneNumber)
                .seat(this.seat)
                .moviePlayingInfo(moviePlayingInfo)
                .classification(this.classification)
                .build();
    }
}
