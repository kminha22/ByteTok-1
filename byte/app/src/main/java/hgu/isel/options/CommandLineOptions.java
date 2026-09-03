package hgu.isel.options;

import org.apache.commons.cli.*;

public class CommandLineOptions {
    private final Options options;

    public CommandLineOptions() {
        options = new Options();


        Option vocab = Option.builder("v")
                .longOpt("vocab")
                .hasArgs()
                .desc("create vocabulary with input path and max length / 1) input path 2) max size 3) generated vocab path")
                .build();


        Option tokenize = Option.builder("a")
                .longOpt("analyze")
                .hasArgs()
                .desc("analyze input class file / 1) input path")
                .build();

        Option json = Option.builder("j")
                .longOpt("json")
                .hasArgs() 
                .desc("Save JSON output of Code attribute / 1) input path 2) output path")
                .build();

        Option multi = Option.builder("m")
                .longOpt("multi")
                .hasArgs() 
                .desc("Save multiple JSON outputs from class path file / 1) class path file path 2) output path")
                .build();

        Option remove = Option.builder("r")
                .longOpt("remove")
                .hasArgs()
                .desc("remove scala / kotlin files which include customized attributes from input directory path / 1) input path")
                .build();

        Option generate = Option.builder("g")
                .longOpt("generate")
                .hasArgs()
                .desc("generate new text files as inputs of pre-trained model / 1) input path 2) base directory for generating")
                .build();

        Option delete = Option.builder("d")
                .longOpt("delete")
                .hasArgs()
                .desc("delete files which have large constant pool / 1) input path")
                .build();

        Option find = Option.builder("f")
                .longOpt("find")
                .hasArgs()
                .desc("find all customized attributes in scala / kotlin class files / 1) input path")
                .build();

        Option extract = Option.builder("e")
                .longOpt("extract")
                .hasArgs()
                .desc("extract all methods from the input path / 1) input path 2) base directory for extracting")
                .build();

        Option search = Option.builder("s")
                .longOpt("search")
                .hasArgs()
                .desc("search specific method with input string / 1) input path 2) method name 3) output directory")
                .build();


        Option searchCSV = Option.builder("sc")
                .longOpt("searchCSV")
                .hasArgs()
                .desc("search specific method with input string / 1) input csv file path 2) method name 3) output directory")
                .build();

        options.addOption(vocab);
        options.addOption(tokenize);
        options.addOption(json);
        options.addOption(multi);
        options.addOption(remove);
        options.addOption(generate);
        options.addOption(delete);
        options.addOption(find);
        options.addOption(extract);
        options.addOption(search);
        options.addOption(searchCSV);

    }

    public CommandLine parse(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }

    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar app.jar", options, true);
    }

}
