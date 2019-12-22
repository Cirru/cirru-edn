
Cirru EDN
----

> EDN data in Cirru format.

Demo http://repo.cirru.org/cirru-edn/

### Usage

[![Respo](https://img.shields.io/clojars/v/cirru/edn.svg)](https://clojars.org/cirru/edn)

```edn
[cirru/edn "0.0.4"]
```

```clojure
(cirru-edn.core/parse "a b")

(cirru-edn.core/write {:a 1})
```

To use [parser from nim](https://github.com/Cirru/parser.nim/):

```bash
yarn add @cirru/parser.nim
```

```clojure
(cirru-edn.nim/parse "a b")
```

### Workflow

Workflow https://github.com/mvc-works/calcit-workflow

### License

MIT
