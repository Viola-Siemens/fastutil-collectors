# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project overview

This repository is a small Java library that provides `java.util.stream.Collector` factories for [fastutil](https://fastutil.di.unimi.it/) primitive collections and maps. The public API is centered on `org.ecnumc.fastutilcollectors.FastUtilCollectors`, with a reusable map collector implementation in `FastUtilMapCollectorImpl`.

The codebase is a single Maven module targeting Java 8 bytecode. Dependencies are minimal: fastutil for primitive collections, JSR-305 for nullability annotations, and JUnit 5 for tests.

## Common commands

- Build and run tests: `mvn test`
- Package without tests: `mvn -DskipTests package`
- Run a single test class: `mvn -Dtest=IntCollectionTest test`
- Run a single test method: `mvn -Dtest=IntCollectionTest#testIntStreamCollect test`

## Architecture notes

- `FastUtilCollectors` is the public entry point. It exposes collector factories for primitive collections and primitive/object maps.
- The primitive collection collectors follow the same pattern: a supplier for the target fastutil collection, an accumulator that adds one element, a combiner that merges two partial collections, and a finisher that can adapt the temporary collection to the requested result type.
- The uniqueness-enforcing collectors reject duplicates during accumulation and combining by throwing `IllegalStateException`.
- `FastUtilMapCollectorImpl` is the shared collector implementation for map-building collectors. It keeps the collector logic generic and delegates key/value extraction to mapper functions.
- The tests currently focus on primitive int collection behavior and map collection behavior. Use them as the reference for expected collector semantics when adding new collector factories.

## Repository conventions

- Public API lives under `src/main/java/org/ecnumc/fastutilcollectors`.
- Tests live under `src/test/java/org/ecnumc/fastutilcollectors`.
- The library uses fastutil primitive types heavily, so check whether a collector should work with primitive-specific value extractors or boxed `Function` mappers before adding a new overload.

## Current state

- Last committed change: `590a652` (`feat(REQ-7): add fastutil collectors support`)
