# emitter

Writes random strings to multiple files opening up one thread per file to written to and continously writing data until exited.

## Usage

```lein deps
```

```lein clean, compile
```

```lein run -m emitter.core --files /tmp/file1,/tmp/file2 --delay 500
```