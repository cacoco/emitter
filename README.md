# emitter

![EMIT](http://www.transtutors.com/Uploadfile/CMS_Images/3278_Common%20Emitter%20n-p-n%20Transistor.JPG)

Writes random strings to multiple files opening up one thread per file to be appended to and continuously writing data until exited. If the files do not exist, they will be created. Passing a delay is optional. If not passed the default of 500 millis will be used.

Output will be in [Common Log Format](http://httpd.apache.org/docs/1.3/logs.html#common), i.e.:

127.0.0.1 - frank [10/Oct/2000:13:55:36 -0700] "GET /apache_pb.gif HTTP/1.0" 200 2326

## Usage

You will first need [Leiningen](https://github.com/technomancy/leiningen) installed.

Run via start script with required comma-separated list of files at which to emit and optional delay parameter:

```./bin/start /tmp/log-1.txt,/tmp/log-2.txt 200
```