package com.ssafy.CommonPJT.dto.Ticket;

import com.ssafy.CommonPJT.domain.MoviePlayingInfo;
import com.ssafy.CommonPJT.domain.Ticket;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TicketSaveDto {

    @Schema(description = "휴대폰 번호")
    private String phoneNumber;

    @Schema(description = "좌석")
    private String seat;

    @Schema(description = "구분")
    private String classification;

    @Schema(description = "영화상영정보 ID")
    private Long moviePlayingInfoId;

    @Schema(description = "결제 금액")
    private String totalCost;

    @Builder
    public Ticket toEntity(MoviePlayingInfo moviePlayingInfo) {
        return Ticket.builder()
                .phoneNumber(this.phoneNumber)
                .seat(this.seat)
                .moviePlayingInfo(moviePlayingInfo)
                .classification(this.classification)
                .reservationTime(new Date())
                .totalCost(this.totalCost)
                .build();
    }
}
