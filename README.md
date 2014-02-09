ArgumentsParser
===============

ArgumentsParser is a  simple java parser for commandline arguments written in Java. Originally it was a kind of finger exercise and an example for [one of my blog posts](http://www.christian-rehn.de/2014/01/19/clean-code-und-das-modellprinzip/) (German). Because it's not far from being a useful library, it might continue working on it until I'm satisfied.


Purpose
-------

The purpose of this library is to provide a convenient way to access commandline arguments. Evaluating the arguments can be quite cumbersome if you do it by hand. With ArgumentParser you can specify arguments of verious types in long (e.g. `--long-argument`) and short form (e.g. `-l`). The command-line arguments are parsed, validated for correct syntax and type and provided to the application through a convenient, type-safe interface.

This library is not intended to grow until it can do everything. There are various styles of how commandline arguments can be used. They typically vary in the number of dashes or slashes used to indicate a long/short form of an argument, which shorthand forms like concatenated options can be used, etc. This library will never support all of them. Rather it provides just one style and tries to make using it handy and straightforward.

There are plenty of other argument parsers for Java out there. See for example [here](https://github.com/search?l=Java&q=arguments+parser&type=Repositories) and [there](http://stackoverflow.com/questions/367706/is-there-a-good-command-line-argument-parser-for-java/). You may especially have a look at the annotation-bases parsers. They are pretty cool.


Features
--------

* All arguments allow for a long form starting with two dashes (e.g. `--long`) and an optional short form starting with one dash (e.g. `-l`)
* There are boolean switches called "options" and argument-carrying parameters of various types (Integer, String, Double, StringList).
* Values directly follow the parameter: `-l value` or `--long value`
* StringList parameters catch all parameters until the end: `--list value1 value2 value3`
* Synatx and type checking
* Meaningful exceptions
* Builder class with a fluent interface to conveniently specify parameters
* ...


Example
-------

```Java
public static void main(String... args) {
    try {
        ArgumentParser arg = new ArgumentParserBuilder()
                .withOption('l')
                .andIntegerParameter('p')
                .andStringParameter('d').build();
        arg.parse(args);
        Boolean logging = arg.isOptionSet('l');
        Integer port = arg.getParameter('p');
        String directory = arg.getParameter('d');
        executeApplication(logging, port, directory);
    } catch (ArgumentParsingException e) {
        System.out.println(e.getMessage());
    }
}
```


Notes
-----

* LGPL licensed
* Uses Java 7
* A nice piece of code for practicing TDD (that what I used it for)
