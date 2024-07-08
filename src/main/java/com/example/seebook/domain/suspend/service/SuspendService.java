package com.example.seebook.domain.suspend.service;

import com.example.seebook.domain.suspend.domain.Suspend;
import com.example.seebook.domain.suspend.dto.SuspendDTO;
import com.example.seebook.domain.suspend.repository.SuspendRepository;
import com.example.seebook.global.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SuspendService {
    private final SuspendRepository suspendRepository;

    public void changeSuspensionPeriod(Long userId, Long period, String reason) {
        Optional<Suspend> optionalSuspend = suspendRepository.findByUserId(userId);

        if (optionalSuspend.isEmpty()) {
            Suspend suspend = Suspend.builder()
                    .userId(userId)
                    .reason(reason)
                    .startDate(LocalDateTime.now())
                    .endDate(LocalDateTime.now().plusDays(period))
                    .build();
            suspendRepository.save(suspend);
        } else {
            Suspend suspend = optionalSuspend.get();
            suspend.changePeriod(period);
            suspendRepository.save(suspend);
        };
    }

    public SuspendDTO getSuspend(Long userId) {
        Optional<Suspend> optionalSuspend = suspendRepository.findByUserId(userId);
        if (optionalSuspend.isPresent()) {
            return SuspendDTO.from(optionalSuspend.get());
        } else {
            return SuspendDTO.notSuspend();
        }
    }

    public void changeSuspendByAdminModify(Long userId, LocalDateTime endDate) {
        Suspend Suspend = suspendRepository.findByUserId(userId)
                .orElseThrow(UserException.NotFoundUserException::new);

        Suspend.changeEndDate(endDate);
        suspendRepository.save(Suspend);
    }

}
