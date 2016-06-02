package com.example.pojo;

/**
 * @author yangkun
 *         generate on 16/4/17
 */
public class Update {
    private final Member member;
    private final String updateText;

    private Update(Builder b) {
        member = b.member;
        updateText = b.updateText;
    }

    public static class Builder implements ObjBuilder<Update> {
        private Member member;
        private String updateText;

        public Builder member(Member member_) {
            member = member_;
            return this;
        }

        public Builder updateText(String updateText_) {
            updateText = updateText_;
            return this;
        }

        @Override
        public Update build() {
            return new Update(this);
        }
    }
}
