package bz.benchmark.runner.capitatize;

import java.util.List;

public class CapitalizeConstant {

    public static List<Holder> TO_CAPITALIZE =
            List.of(
                    new Holder("", ""),

                    new Holder("!", "!"),
                    new Holder(". ", ""),
                    new Holder(". . ", ""),
                    new Holder(".\t", ""),
                    new Holder(" ? ", " ? "),

                    new Holder("H", "H"),
                    new Holder("g", "G"),

                    new Holder("HELLO WORLD! ! ! ", "Hello world! "),
                    new Holder("Hello World. ?", "Hello world. ?"),
                    new Holder("hello world.", "Hello world."),

                    new Holder("HELLO WORLD. LOREM IPSUM.", "Hello world. Lorem ipsum."),
                    new Holder("Hello World. Lorem Ipsum.", "Hello world. Lorem ipsum."),
                    new Holder("hello world. lorem ipsum.", "Hello world. Lorem ipsum."),

                    new Holder("Hello!World.lorem?Ipsum.", "Hello!world.lorem?ipsum."),

                    new Holder("HELLO WORLD, TEST.", "Hello world, test."),
                    new Holder("HELLO WORLD; TEST.", "Hello world; test."),
                    new Holder("HELLO WORLD: TEST.", "Hello world: test."),
                    new Holder("HELLO WORLD! TEST.", "Hello world! Test."),
                    new Holder("HELLO WORLD? TEST.", "Hello world? Test."),

                    new Holder("HELLO 12% TEST.", "Hello 12% test."),
                    new Holder("HELLO 12€ TEST.", "Hello 12€ test."),
                    new Holder("HELLO 12$ TEST.", "Hello 12$ test."),
                    new Holder("HELLO 12/13 TEST.", "Hello 12/13 test."),

                    new Holder("HELLO WORLD", "Hello world"),
                    new Holder("Hello World", "Hello world"),
                    new Holder("hello world", "Hello world"),

                    new Holder("! HELLO WORLD! ", "Hello world! "),
                    new Holder("! HELLO WORLD ! ", "Hello world ! "),
                    new Holder("! Hello World!", "Hello world!"),
                    new Holder("!  hello world!  hello world", " hello world!  hello world"),

                    new Holder("Question ? ? danger", "Question ? Danger"),
                    new Holder("Question ?  ? danger", "Question ?  ? Danger"),
                    new Holder("Question ?\u00A0\u00A0? danger", "Question ?\u00A0\u00A0? Danger"),
                    new Holder("Question ?   ? danger", "Question ?   ? Danger"),
                    new Holder("Question ? ? ? danger", "Question ? Danger"),
                    new Holder("Point . . danger", "Point . Danger"),
                    new Holder("Exclamation ! ! danger", "Exclamation ! Danger"),

                    new Holder("est.   qui dolorem ipsum", "Est.   qui dolorem ipsum"),
                    new Holder("est.   qui dolorem ipsum. a", "Est.   qui dolorem ipsum. A"),
                    new Holder("A lot of ! ? . ? ? ! . danger!?", "A lot of ! Danger!?"),
                    new Holder("A lot of! ? . ? ? ! . danger !?", "A lot of! Danger !?"),
                    new Holder("A lot of !!!! ??? .. ? ? ! . danger!?", "A lot of !!!! ??? .. Danger!?"),
                    new Holder("!? lorem \uD83D\uDE00 \uD83D\uDE03", "!? Lorem \uD83D\uDE00 \uD83D\uDE03"),

                    new Holder(
                            " Neque porro quisquam ? est qui dolorem ipsum ! quia dolor sit amet. consectetur, adipisci velit… ",
                            " neque porro quisquam ? Est qui dolorem ipsum ! Quia dolor sit amet. Consectetur, adipisci velit… "
                    )
            );

    public static class Holder {
        private final String input;
        private final String output;

        public Holder(String input, String output) {
            this.input = input;
            this.output = output;
        }

        public String getInput() {
            return input;
        }

        public String getOutput() {
            return output;
        }

        @Override
        public String toString() {
            return input;
        }
    }


}
