
Cirru EDN
----

> EDN data in Cirru format.

Demo http://repo.cirru.org/cirru-edn/

### Usage

[![Respo](https://img.shields.io/clojars/v/cirru/edn.svg)](https://clojars.org/cirru/edn)

```edn
[cirru/edn "0.0.9-a1"]
```

```clojure
(cirru-edn.core/parse "a b")

(cirru-edn.core/write {:a 1})
```

To represent single liternals, Cirru EDN requires an extra `do x` syntax, for example:

```cirru
do :something
```

### use Nim parser(experimental. can be slow)

To use [parser from nim](https://github.com/Cirru/parser.nim/):

```bash
yarn add @cirru/parser.nim
```

```clojure
(cirru-edn.nim/parse "a b")
```

### Syntax

Cirru EDN is based on [Cirru Text Syntax](http://text.cirru.org/), on top of which there is specific syntax for EDN:

Vectors, lists and sets:

```cirru
[] 1 2 3
```

```cirru
list 1 2 3
```

```cirru
set 1 2 3
```

Hashmap:

```cirru
{}
  :a 1
  :b 3
```

Literals, since Cirru use lines for expressions, need `do` for extracting values:

```cirru
do 1
```

```cirru
do :k
```

```cirru
do nil
```

Strings need to be prefixed with a `|`:

```cirru
do |short
```

```cirru
do "|long text"
```

### Workflow

Workflow https://github.com/mvc-works/calcit-workflow

### License

MIT
