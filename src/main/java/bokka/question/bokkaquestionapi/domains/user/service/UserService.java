package bokka.question.bokkaquestionapi.domains.user.service;

import bokka.question.bokkaquestionapi.common.enums.Tier;
import bokka.question.bokkaquestionapi.domains.rank.repository.Rank;
import bokka.question.bokkaquestionapi.domains.rank.repository.RankTier;
import bokka.question.bokkaquestionapi.domains.rank.repository.RankTierRepository;
import bokka.question.bokkaquestionapi.domains.user.dto.request.CreateUserDto;
import bokka.question.bokkaquestionapi.domains.user.dto.request.UpdateUserDto;
import bokka.question.bokkaquestionapi.domains.user.repository.User;
import bokka.question.bokkaquestionapi.domains.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RankTierRepository rankTierRepository;

    public void saveUser(CreateUserDto createUserDto) {
        RankTier rankTier = rankTierRepository.findById(Tier.BRONZE).orElseThrow();
        Rank newRank = Rank.builder().rankTier(rankTier).build();
        User newUser = User.builder().userSeq(createUserDto.getUserSeq()).name(createUserDto.getName()).rank(newRank).build();
        userRepository.save(newUser);
    }

    @Transactional
    public void updateUserName(UpdateUserDto updateUserDto) {
        User user = userRepository.findById(updateUserDto.getUserSeq()).orElseThrow();
        user.updateUserName(updateUserDto.getName());
    }

    public String findUserName(String userSeq) {
        return userRepository.findUserNameByUserSeq(userSeq).orElseThrow();
    }

}
