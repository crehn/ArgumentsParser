ArgumentsParser
===============

ArgumentsParser is a  simple java parser for commandline arguments written in Java. Originally it was a kind of finger exercise and an example for [one of my blog posts](http://www.christian-rehn.de/2014/01/19/clean-code-und-das-modellprinzip/) (German). Because it's not far from being a useful library, it might continue working on it until I'm satisfied.


Purpose
-------

The purpose of this library is to provide a convenient way to accesws commandline arguments. Evaluating the arguments can be quite cumbersome if you do it by hand. With ArgumentParser you can specify arguments of verious types in long (e.g. ```--long-argument```) and short form (e.g. ```-l```). The command-line arguments are parsed, validated for correct syntax and type and provided to the application through a convenient, type-safe interface.

This library is not intended to grow until it can do anything. There are various styles of how commandline arguments can be used. They tapically vary in the number of dashes or slashes used to indicate a long/short form of an argument, which shorthand forms like concatenated options can be used, etc. This library will never support all of them. Rather it provides just one style and tries to make using it handy and straightforward.
