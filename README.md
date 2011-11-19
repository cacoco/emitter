# emitter

Writes random strings to multiple files opening up one thread per file to be appended to and continously writing data until exited. If the files do not exist, they will be created. Passing a delay is optional. If not passed the default of 500 millis will be used.

## Usage

```lein deps
```

```lein clean, compile
```

```lein run -m emitter.core --files /tmp/file1,/tmp/file2 [--delay 500]
```