# ReplaceIt

ReplaceIt is a Burp Suite extension designed to streamline the process of finding and replacing text within HTTP requests directly from the Repeater tab. Instead of manually editing requests, ReplaceIt allows users to highlight occurrences of a search term and replace them efficiently with a few clicks.

## Features

- **Find and Replace**: Quickly find and replace text within HTTP requests.
- **Highlighting**: Highlight all occurrences of a search term within the request.

## Installation

1. Download the latest JAR file from the [Releases](https://github.com/anzuukino/ReplaceIt/releases/tag/v1.0.0)
2. Open Burp Suite and navigate to the `Extension` tab
3. Click on `Add` and select the JAR file you downloaded
4. You should now see the log message `ReplaceIt extension loaded` and the extension should be available in the `Repeater` tab

## Build

1. Clone the repository
2. Run `mvn clean package`
3. The JAR file will be available in the `target` directory