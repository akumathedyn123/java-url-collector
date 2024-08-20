## ExtractLinks - A Java Web Crawler

This Java program extracts links from a list of URLs, saves the extracted links to files, and removes the processed URLs from the input list. 

### How to Clone

1.  Open your terminal or command prompt.
2.  Navigate to the directory where you want to clone the repository.
3.  Use the following command to clone the repository:

```bash
git clone https://github.com/akumathedyn123/java-url-collector.git
```


### How to Run

**Prerequisites:**

* Java 11 or above installed on your system.
* Jsoup library ([https://jsoup.org/download](https://jsoup.org/download)) added to your project. You can download the Jsoup jar file and place it in the same directory as your project or use a dependency management tool like Maven or Gradle.

**Steps:**

1.  Open a terminal or command prompt and navigate to the project directory.
2.  Compile the Java source code:

```bash
javac main.java
```

This will create an executable file named `main.class`.

3.  Run the program with the following command:

```bash
java main [arguments]
```

**Arguments:**

* `-i|--input <filename>`: Path to the text file containing the list of URLs (default: urls.txt).
* `-o|--output <directory>`: Directory path to save the extracted link files (default: /path/to/text).

**Example Usage:**

```bash
java main -i my_urls.txt -o extracted_links
```

This will process the URLs in `my_urls.txt` and save the extracted links to files in the `extracted_links` directory.

### How it Works

1. The program reads a list of URLs from a text file.
2. It iterates through each URL in the list.
3. For each URL, it downloads the HTML content using the `downloadHtml` function.
4. It then parses the downloaded HTML with Jsoup to extract links from anchor tags (`<a>`) with `href` attributes using the `extractLinks` function.
5. The extracted links are saved to a file named after the processed URL using the `saveLinksToFile` function.
6. Finally, the processed URL is removed from the input file using the `deleteUrl` function.


### License

This project is licensed under the MIT License (see LICENSE file for details).

### Contribution

We welcome contributions to this project. Feel free to fork the repository and submit pull requests with your improvements.
