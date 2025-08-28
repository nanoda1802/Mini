package managers.validation;

import configs.validation.FailureReason;
import configs.validation.RegEx;
import utils.Pair;

import java.util.regex.Pattern;

public class BrowseMembersValidator extends Validator {

    @Override
    public Pair<Boolean, String> check(String target) {
        boolean isBrowseSelectValid = Pattern.matches(RegEx.MEMBER_BROWSE_CONDITION.getPattern(),  target);
        if  (!isBrowseSelectValid) {
            return new Pair<>(false, FailureReason.MEMBER_BROWSE_CONDITION.getReason());
        }
        return new Pair<>(true, target);
    }
}
