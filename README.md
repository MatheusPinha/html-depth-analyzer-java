# HTML Analyzer - Axur Technical Challenge

This program analyzes an HTML structure from a given URL and identifies the text segment located at the deepest nesting level.

## Requirements
* JDK 17
* No external libraries or frameworks

## How to Compile
From the directory containing the source code, run:
```bash
javac HtmlAnalyzer.java

## How to Run
Execute the program by providing a valid URL as an argument:
java HtmlAnalyzer <URL>

## Features
Deepest Text Identification - Returns the first text segment at the maximum depth level.
HTML Validation (Bonus) - Identifies malformed HTML structures and returns "malformed HTML".
Error Handling - "URL connection error" if the content cannot be retrieved.
