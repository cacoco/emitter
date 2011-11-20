# emitter

![EMIT](http://www.transtutors.com/Uploadfile/CMS_Images/3278_Common%20Emitter%20n-p-n%20Transistor.JPG)

Writes random strings to multiple files opening up one thread per file to be appended to and continously writing data until exited. If the files do not exist, they will be created. Passing a delay is optional. If not passed the default of 500 millis will be used.

## Usage

Run via start script with required comma-separated list of files at which to emit and optional delay parameter:

```./bin/start /tmp/log-1.txt,/tmp/log-2.txt 200
```