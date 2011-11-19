# emitter

Writes random strings to multiple files opening up one thread per file to be appended to and continously writing data until exited. Currently requires the files to exist *A priori*.

## Usage

```lein deps
```

```lein clean, compile
```

```lein run -m emitter.core --files /tmp/file1,/tmp/file2 --delay 500
```