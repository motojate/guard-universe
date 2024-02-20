package bokka.question.bokkaquestionapi.domains.user.service;

import bokka.question.bokkaquestionapi.common.constant.Data;
import bokka.question.bokkaquestionapi.common.enums.Tier;
import bokka.question.bokkaquestionapi.domains.rank.repository.Rank;
import bokka.question.bokkaquestionapi.domains.rank.repository.RankTier;
import bokka.question.bokkaquestionapi.domains.rank.repository.RankTierRepository;
import bokka.question.bokkaquestionapi.domains.user.dto.request.UpdateUserDto;
import bokka.question.bokkaquestionapi.domains.user.repository.User;
import bokka.question.bokkaquestionapi.domains.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RankTierRepository rankTierRepository;

    private String generateUserName() {
        String randomName = Data.USER_RANDOM_NAMES.get(new Random().nextInt(Data.USER_RANDOM_NAMES.size()));
        int randomNumber = new Random().nextInt(10000000);
        String formattedNumber = String.format("%07d", randomNumber);
        String UserName = randomName + formattedNumber;
        boolean isUniqueUserName = checkUserNameUnique(UserName);
        if(isUniqueUserName) return UserName;
        else return generateUserName();
    }

    private boolean checkUserNameUnique(String userName) {
        return userRepository.countByName(userName) == 0;
    }

    public void saveUser(String userSeq) {
        String userName = generateUserName();
        RankTier rankTier = rankTierRepository.findById(Tier.BRONZE).orElseThrow();
        Rank newRank = Rank.builder().rankTier(rankTier).build();
        User newUser = User.builder().userSeq(userSeq).name(userName).rank(newRank).build();
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
