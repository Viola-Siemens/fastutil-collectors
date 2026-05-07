package org.ecnumc.fastutilcollectors;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.bytes.*;
import it.unimi.dsi.fastutil.chars.*;
import it.unimi.dsi.fastutil.doubles.*;
import it.unimi.dsi.fastutil.floats.*;
import it.unimi.dsi.fastutil.ints.*;
import it.unimi.dsi.fastutil.longs.*;
import it.unimi.dsi.fastutil.objects.*;
import it.unimi.dsi.fastutil.shorts.*;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Main API
 * @author liudongyu
 */
public final class FastUtilCollectors {
	/** Identity characteristics */
	public static final Set<Collector.Characteristics> CH_ID = Collections.unmodifiableSet(
			EnumSet.of(Collector.Characteristics.IDENTITY_FINISH)
	);

	/**
	 * Create a collector for int streams
	 * @param <M> temporary IntCollection type
	 * @param <T> IntCollection type
	 * @param supplier temporary IntCollection supplier
	 * @param finisher IntCollection finisher
	 * @return collector
	 */
	@SuppressWarnings("deprecation")
	public static <M extends IntCollection, T extends IntCollection> Collector<Integer, M, T>
	toIntCollection(Supplier<M> supplier, Function<M, T> finisher) {
		return new Collector<Integer, M, T>() {
			@Override
			public Supplier<M> supplier() {
				return supplier;
			}

			@Override
			public BiConsumer<M, Integer> accumulator() {
				return IntCollection::add;
			}

			@Override
			public BinaryOperator<M> combiner() {
				return (a, b) -> {
					a.addAll(b);
					return a;
				};
			}

			@Override
			public Function<M, T> finisher() {
				return finisher;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return CH_ID;
			}
		};
	}

	/**
	 * Create a uniqueness-enforcing collector for int streams
	 * @param <M> temporary IntCollection type
	 * @param <T> IntCollection type
	 * @param supplier temporary IntCollection supplier
	 * @param finisher IntCollection finisher
	 * @return collector
	 * @throws IllegalStateException if the stream contains duplicate elements
	 */
	@SuppressWarnings("deprecation")
	public static <M extends IntCollection, T extends IntCollection> Collector<Integer, M, T>
	toUniqueIntCollection(Supplier<M> supplier, Function<M, T> finisher) {
		return new Collector<Integer, M, T>() {
			@Override
			public Supplier<M> supplier() {
				return supplier;
			}

			@Override
			public BiConsumer<M, Integer> accumulator() {
				return (a, e) -> {
					if(!a.add(e)) {
						throw duplicateElementException(e);
					}
				};
			}

			@Override
			public BinaryOperator<M> combiner() {
				return (a, b) -> {
					b.forEach(e -> {
						if(!a.add(e)) {
							throw duplicateElementException(e);
						}
					});
					return a;
				};
			}

			@Override
			public Function<M, T> finisher() {
				return finisher;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return CH_ID;
			}
		};
	}

	/**
	 * Creates a collector for boolean streams.
	 *
	 * @param <M>      temporary BooleanCollection type
	 * @param <T>      BooleanCollection type
	 * @param supplier temporary BooleanCollection supplier
	 * @param finisher BooleanCollection finisher
	 * @return collector
	 */
	@SuppressWarnings("deprecation")
	public static <M extends BooleanCollection, T extends BooleanCollection> Collector<Boolean, M, T>
	toBooleanCollection(Supplier<M> supplier, Function<M, T> finisher) {
		return new Collector<Boolean, M, T>() {
			@Override
			public Supplier<M> supplier() {
				return supplier;
			}

			@Override
			public BiConsumer<M, Boolean> accumulator() {
				return BooleanCollection::add;
			}

			@Override
			public BinaryOperator<M> combiner() {
				return (a, b) -> {
					a.addAll(b);
					return a;
				};
			}

			@Override
			public Function<M, T> finisher() {
				return finisher;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return CH_ID;
			}
		};
	}

	/**
	 * Creates a uniqueness-enforcing collector for boolean streams.
	 *
	 * @param <M>      temporary BooleanCollection type
	 * @param <T>      BooleanCollection type
	 * @param supplier temporary BooleanCollection supplier
	 * @param finisher BooleanCollection finisher
	 * @return collector
	 * @throws IllegalStateException if the stream contains duplicate elements
	 */
	@SuppressWarnings("deprecation")
	public static <M extends BooleanCollection, T extends BooleanCollection> Collector<Boolean, M, T>
	toUniqueBooleanCollection(Supplier<M> supplier, Function<M, T> finisher) {
		return new Collector<Boolean, M, T>() {
			@Override
			public Supplier<M> supplier() {
				return supplier;
			}

			@Override
			public BiConsumer<M, Boolean> accumulator() {
				return (a, e) -> {
					if (!a.add(e)) {
						throw duplicateElementException(e);
					}
				};
			}

			@Override
			public BinaryOperator<M> combiner() {
				return (a, b) -> {
					b.forEach(e -> {
						if(!a.add(e)) {
							throw duplicateElementException(e);
						}
					});
					return a;
				};
			}

			@Override
			public Function<M, T> finisher() {
				return finisher;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return CH_ID;
			}
		};
	}

	/**
	 * Creates a collector for byte streams.
	 *
	 * @param <M>      temporary ByteCollection type
	 * @param <T>      ByteCollection type
	 * @param supplier temporary ByteCollection supplier
	 * @param finisher ByteCollection finisher
	 * @return collector
	 */
	@SuppressWarnings("deprecation")
	public static <M extends ByteCollection, T extends ByteCollection> Collector<Byte, M, T>
	toByteCollection(Supplier<M> supplier, Function<M, T> finisher) {
		return new Collector<Byte, M, T>() {
			@Override
			public Supplier<M> supplier() {
				return supplier;
			}

			@Override
			public BiConsumer<M, Byte> accumulator() {
				return ByteCollection::add;
			}

			@Override
			public BinaryOperator<M> combiner() {
				return (a, b) -> {
					a.addAll(b);
					return a;
				};
			}

			@Override
			public Function<M, T> finisher() {
				return finisher;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return CH_ID;
			}
		};
	}

	/**
	 * Creates a uniqueness-enforcing collector for byte streams.
	 *
	 * @param <M>      temporary ByteCollection type
	 * @param <T>      ByteCollection type
	 * @param supplier temporary ByteCollection supplier
	 * @param finisher ByteCollection finisher
	 * @return collector
	 * @throws IllegalStateException if the stream contains duplicate elements
	 */
	@SuppressWarnings("deprecation")
	public static <M extends ByteCollection, T extends ByteCollection> Collector<Byte, M, T>
	toUniqueByteCollection(Supplier<M> supplier, Function<M, T> finisher) {
		return new Collector<Byte, M, T>() {
			@Override
			public Supplier<M> supplier() {
				return supplier;
			}

			@Override
			public BiConsumer<M, Byte> accumulator() {
				return (a, e) -> {
					if (!a.add(e)) {
						throw duplicateElementException(e);
					}
				};
			}

			@Override
			public BinaryOperator<M> combiner() {
				return (a, b) -> {
					b.forEach(e -> {
						if(!a.add(e)) {
							throw duplicateElementException(e);
						}
					});
					return a;
				};
			}

			@Override
			public Function<M, T> finisher() {
				return finisher;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return CH_ID;
			}
		};
	}

	/**
	 * Creates a collector for char streams.
	 *
	 * @param <M>      temporary CharCollection type
	 * @param <T>      CharCollection type
	 * @param supplier temporary CharCollection supplier
	 * @param finisher CharCollection finisher
	 * @return collector
	 */
	@SuppressWarnings("deprecation")
	public static <M extends CharCollection, T extends CharCollection> Collector<Character, M, T>
	toCharCollection(Supplier<M> supplier, Function<M, T> finisher) {
		return new Collector<Character, M, T>() {
			@Override
			public Supplier<M> supplier() {
				return supplier;
			}

			@Override
			public BiConsumer<M, Character> accumulator() {
				return CharCollection::add;
			}

			@Override
			public BinaryOperator<M> combiner() {
				return (a, b) -> {
					a.addAll(b);
					return a;
				};
			}

			@Override
			public Function<M, T> finisher() {
				return finisher;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return CH_ID;
			}
		};
	}

	/**
	 * Creates a uniqueness-enforcing collector for char streams.
	 *
	 * @param <M>      temporary CharCollection type
	 * @param <T>      CharCollection type
	 * @param supplier temporary CharCollection supplier
	 * @param finisher CharCollection finisher
	 * @return collector
	 * @throws IllegalStateException if the stream contains duplicate elements
	 */
	@SuppressWarnings("deprecation")
	public static <M extends CharCollection, T extends CharCollection> Collector<Character, M, T>
	toUniqueCharCollection(Supplier<M> supplier, Function<M, T> finisher) {
		return new Collector<Character, M, T>() {
			@Override
			public Supplier<M> supplier() {
				return supplier;
			}

			@Override
			public BiConsumer<M, Character> accumulator() {
				return (a, e) -> {
					if (!a.add(e)) {
						throw duplicateElementException(e);
					}
				};
			}

			@Override
			public BinaryOperator<M> combiner() {
				return (a, b) -> {
					b.forEach(e -> {
						if(!a.add(e)) {
							throw duplicateElementException(e);
						}
					});
					return a;
				};
			}

			@Override
			public Function<M, T> finisher() {
				return finisher;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return CH_ID;
			}
		};
	}

	/**
	 * Creates a collector for double streams.
	 *
	 * @param <M>      temporary DoubleCollection type
	 * @param <T>      DoubleCollection type
	 * @param supplier temporary DoubleCollection supplier
	 * @param finisher DoubleCollection finisher
	 * @return collector
	 */
	@SuppressWarnings("deprecation")
	public static <M extends DoubleCollection, T extends DoubleCollection> Collector<Double, M, T>
	toDoubleCollection(Supplier<M> supplier, Function<M, T> finisher) {
		return new Collector<Double, M, T>() {
			@Override
			public Supplier<M> supplier() {
				return supplier;
			}

			@Override
			public BiConsumer<M, Double> accumulator() {
				return DoubleCollection::add;
			}

			@Override
			public BinaryOperator<M> combiner() {
				return (a, b) -> {
					a.addAll(b);
					return a;
				};
			}

			@Override
			public Function<M, T> finisher() {
				return finisher;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return CH_ID;
			}
		};
	}

	/**
	 * Creates a uniqueness-enforcing collector for double streams.
	 *
	 * @param <M>      temporary DoubleCollection type
	 * @param <T>      DoubleCollection type
	 * @param supplier temporary DoubleCollection supplier
	 * @param finisher DoubleCollection finisher
	 * @return collector
	 * @throws IllegalStateException if the stream contains duplicate elements
	 */
	@SuppressWarnings("deprecation")
	public static <M extends DoubleCollection, T extends DoubleCollection> Collector<Double, M, T>
	toUniqueDoubleCollection(Supplier<M> supplier, Function<M, T> finisher) {
		return new Collector<Double, M, T>() {
			@Override
			public Supplier<M> supplier() {
				return supplier;
			}

			@Override
			public BiConsumer<M, Double> accumulator() {
				return (a, e) -> {
					if (!a.add(e)) {
						throw duplicateElementException(e);
					}
				};
			}

			@Override
			public BinaryOperator<M> combiner() {
				return (a, b) -> {
					b.forEach(e -> {
						if(!a.add(e)) {
							throw duplicateElementException(e);
						}
					});
					return a;
				};
			}

			@Override
			public Function<M, T> finisher() {
				return finisher;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return CH_ID;
			}
		};
	}

	/**
	 * Creates a collector for float streams.
	 *
	 * @param <M>      temporary FloatCollection type
	 * @param <T>      FloatCollection type
	 * @param supplier temporary FloatCollection supplier
	 * @param finisher FloatCollection finisher
	 * @return collector
	 */
	@SuppressWarnings("deprecation")
	public static <M extends FloatCollection, T extends FloatCollection> Collector<Float, M, T>
	toFloatCollection(Supplier<M> supplier, Function<M, T> finisher) {
		return new Collector<Float, M, T>() {
			@Override
			public Supplier<M> supplier() {
				return supplier;
			}

			@Override
			public BiConsumer<M, Float> accumulator() {
				return FloatCollection::add;
			}

			@Override
			public BinaryOperator<M> combiner() {
				return (a, b) -> {
					a.addAll(b);
					return a;
				};
			}

			@Override
			public Function<M, T> finisher() {
				return finisher;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return CH_ID;
			}
		};
	}

	/**
	 * Creates a uniqueness-enforcing collector for float streams.
	 *
	 * @param <M>      temporary FloatCollection type
	 * @param <T>      FloatCollection type
	 * @param supplier temporary FloatCollection supplier
	 * @param finisher FloatCollection finisher
	 * @return collector
	 * @throws IllegalStateException if the stream contains duplicate elements
	 */
	@SuppressWarnings("deprecation")
	public static <M extends FloatCollection, T extends FloatCollection> Collector<Float, M, T>
	toUniqueFloatCollection(Supplier<M> supplier, Function<M, T> finisher) {
		return new Collector<Float, M, T>() {
			@Override
			public Supplier<M> supplier() {
				return supplier;
			}

			@Override
			public BiConsumer<M, Float> accumulator() {
				return (a, e) -> {
					if (!a.add(e)) {
						throw duplicateElementException(e);
					}
				};
			}

			@Override
			public BinaryOperator<M> combiner() {
				return (a, b) -> {
					b.forEach(e -> {
						if(!a.add(e)) {
							throw duplicateElementException(e);
						}
					});
					return a;
				};
			}

			@Override
			public Function<M, T> finisher() {
				return finisher;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return CH_ID;
			}
		};
	}

	/**
	 * Creates a collector for long streams.
	 *
	 * @param <M>      temporary LongCollection type
	 * @param <T>      LongCollection type
	 * @param supplier temporary LongCollection supplier
	 * @param finisher LongCollection finisher
	 * @return collector
	 */
	@SuppressWarnings("deprecation")
	public static <M extends LongCollection, T extends LongCollection> Collector<Long, M, T>
	toLongCollection(Supplier<M> supplier, Function<M, T> finisher) {
		return new Collector<Long, M, T>() {
			@Override
			public Supplier<M> supplier() {
				return supplier;
			}

			@Override
			public BiConsumer<M, Long> accumulator() {
				return LongCollection::add;
			}

			@Override
			public BinaryOperator<M> combiner() {
				return (a, b) -> {
					a.addAll(b);
					return a;
				};
			}

			@Override
			public Function<M, T> finisher() {
				return finisher;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return CH_ID;
			}
		};
	}

	/**
	 * Creates a uniqueness-enforcing collector for long streams.
	 *
	 * @param <M>      temporary LongCollection type
	 * @param <T>      LongCollection type
	 * @param supplier temporary LongCollection supplier
	 * @param finisher LongCollection finisher
	 * @return collector
	 * @throws IllegalStateException if the stream contains duplicate elements
	 */
	@SuppressWarnings("deprecation")
	public static <M extends LongCollection, T extends LongCollection> Collector<Long, M, T>
	toUniqueLongCollection(Supplier<M> supplier, Function<M, T> finisher) {
		return new Collector<Long, M, T>() {
			@Override
			public Supplier<M> supplier() {
				return supplier;
			}

			@Override
			public BiConsumer<M, Long> accumulator() {
				return (a, e) -> {
					if (!a.add(e)) {
						throw duplicateElementException(e);
					}
				};
			}

			@Override
			public BinaryOperator<M> combiner() {
				return (a, b) -> {
					b.forEach(e -> {
						if(!a.add(e)) {
							throw duplicateElementException(e);
						}
					});
					return a;
				};
			}

			@Override
			public Function<M, T> finisher() {
				return finisher;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return CH_ID;
			}
		};
	}

	/**
	 * Creates a collector for short streams.
	 *
	 * @param <M>      temporary ShortCollection type
	 * @param <T>      ShortCollection type
	 * @param supplier temporary ShortCollection supplier
	 * @param finisher ShortCollection finisher
	 * @return collector
	 */
	@SuppressWarnings("deprecation")
	public static <M extends ShortCollection, T extends ShortCollection> Collector<Short, M, T>
	toShortCollection(Supplier<M> supplier, Function<M, T> finisher) {
		return new Collector<Short, M, T>() {
			@Override
			public Supplier<M> supplier() {
				return supplier;
			}

			@Override
			public BiConsumer<M, Short> accumulator() {
				return ShortCollection::add;
			}

			@Override
			public BinaryOperator<M> combiner() {
				return (a, b) -> {
					a.addAll(b);
					return a;
				};
			}

			@Override
			public Function<M, T> finisher() {
				return finisher;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return CH_ID;
			}
		};
	}

	/**
	 * Creates a uniqueness-enforcing collector for short streams.
	 *
	 * @param <M>      temporary ShortCollection type
	 * @param <T>      ShortCollection type
	 * @param supplier temporary ShortCollection supplier
	 * @param finisher ShortCollection finisher
	 * @return collector
	 * @throws IllegalStateException if the stream contains duplicate elements
	 */
	@SuppressWarnings("deprecation")
	public static <M extends ShortCollection, T extends ShortCollection> Collector<Short, M, T>
	toUniqueShortCollection(Supplier<M> supplier, Function<M, T> finisher) {
		return new Collector<Short, M, T>() {
			@Override
			public Supplier<M> supplier() {
				return supplier;
			}

			@Override
			public BiConsumer<M, Short> accumulator() {
				return (a, e) -> {
					if (!a.add(e)) {
						throw duplicateElementException(e);
					}
				};
			}

			@Override
			public BinaryOperator<M> combiner() {
				return (a, b) -> {
					b.forEach(e -> {
						if(!a.add(e)) {
							throw duplicateElementException(e);
						}
					});
					return a;
				};
			}

			@Override
			public Function<M, T> finisher() {
				return finisher;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return CH_ID;
			}
		};
	}

	/**
	 * Creates a collector for object streams.
	 *
	 * @param <E>      stream element type
	 * @param <M>      temporary ObjectCollection type
	 * @param <T>      ObjectCollection type
	 * @param supplier temporary ObjectCollection supplier
	 * @param finisher ObjectCollection finisher
	 * @return collector
	 */
	public static <E, M extends ObjectCollection<E>, T extends ObjectCollection<E>> Collector<E, M, T>
	toObjectCollection(Supplier<M> supplier, Function<M, T> finisher) {
		return new Collector<E, M, T>() {
			@Override
			public Supplier<M> supplier() {
				return supplier;
			}

			@Override
			public BiConsumer<M, E> accumulator() {
				return ObjectCollection::add;
			}

			@Override
			public BinaryOperator<M> combiner() {
				return (a, b) -> {
					a.addAll(b);
					return a;
				};
			}

			@Override
			public Function<M, T> finisher() {
				return finisher;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return CH_ID;
			}
		};
	}

	/**
	 * Creates a uniqueness-enforcing collector for object streams.
	 *
	 * @param <E>      stream element type
	 * @param <M>      temporary ObjectCollection type
	 * @param <T>      ObjectCollection type
	 * @param supplier temporary ObjectCollection supplier
	 * @param finisher ObjectCollection finisher
	 * @return collector
	 * @throws IllegalStateException if the stream contains duplicate elements
	 */
	public static <E, M extends ObjectCollection<E>, T extends ObjectCollection<E>> Collector<E, M, T>
	toUniqueObjectCollection(Supplier<M> supplier, Function<M, T> finisher) {
		return new Collector<E, M, T>() {
			@Override
			public Supplier<M> supplier() {
				return supplier;
			}

			@Override
			public BiConsumer<M, E> accumulator() {
				return (a, e) -> {
					if (!a.add(e)) {
						throw duplicateElementException(e);
					}
				};
			}

			@Override
			public BinaryOperator<M> combiner() {
				return (a, b) -> {
					b.forEach(e -> {
						if(!a.add(e)) {
							throw duplicateElementException(e);
						}
					});
					return a;
				};
			}

			@Override
			public Function<M, T> finisher() {
				return finisher;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return CH_ID;
			}
		};
	}

	/**
	 * Creates a collector for building an {@link Int2BooleanMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor int key extractor
	 * @param valueExtractor boolean value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Int2BooleanMap>
	toInt2BooleanMap(Function<O, Integer> keyExtractor, Function<O, Boolean> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Int2BooleanOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building an {@link Int2ByteMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor int key extractor
	 * @param valueExtractor byte value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Int2ByteMap>
	toInt2ByteMap(Function<O, Integer> keyExtractor, Function<O, Byte> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Int2ByteOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building an {@link Int2CharMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor int key extractor
	 * @param valueExtractor char value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Int2CharMap>
	toInt2CharMap(Function<O, Integer> keyExtractor, Function<O, Character> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Int2CharOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building an {@link Int2DoubleMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor int key extractor
	 * @param valueExtractor double value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Int2DoubleMap>
	toInt2DoubleMap(Function<O, Integer> keyExtractor, Function<O, Double> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Int2DoubleOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building an {@link Int2FloatMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor int key extractor
	 * @param valueExtractor float value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Int2FloatMap>
	toInt2FloatMap(Function<O, Integer> keyExtractor, Function<O, Float> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Int2FloatOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building an {@link Int2IntMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor int key extractor
	 * @param valueExtractor int value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Int2IntMap>
	toInt2IntMap(Function<O, Integer> keyExtractor, Function<O, Integer> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Int2IntOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building an {@link Int2LongMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor int key extractor
	 * @param valueExtractor long value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Int2LongMap>
	toInt2LongMap(Function<O, Integer> keyExtractor, Function<O, Long> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Int2LongOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building an {@link Int2ShortMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor int key extractor
	 * @param valueExtractor short value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Int2ShortMap>
	toInt2ShortMap(Function<O, Integer> keyExtractor, Function<O, Short> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Int2ShortOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building an {@link Int2ObjectMap}.
	 *
	 * @param <O> stream element type
	 * @param <T> map value type
	 * @param keyExtractor int key extractor
	 * @param valueExtractor object value extractor
	 * @return collector
	 */
	public static <O, T> Collector<O, ?, Int2ObjectMap<T>>
	toInt2ObjectMap(Function<O, Integer> keyExtractor, Function<O, T> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Int2ObjectOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Byte2BooleanMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor byte key extractor
	 * @param valueExtractor boolean value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Byte2BooleanMap>
	toByte2BooleanMap(Function<O, Byte> keyExtractor, Object2BooleanFunction<O> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Byte2BooleanOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Byte2ByteMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor byte key extractor
	 * @param valueExtractor byte value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Byte2ByteMap>
	toByte2ByteMap(Function<O, Byte> keyExtractor, Function<O, Byte> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Byte2ByteOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Byte2CharMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor byte key extractor
	 * @param valueExtractor char value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Byte2CharMap>
	toByte2CharMap(Function<O, Byte> keyExtractor, Function<O, Character> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Byte2CharOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Byte2DoubleMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor byte key extractor
	 * @param valueExtractor double value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Byte2DoubleMap>
	toByte2DoubleMap(Function<O, Byte> keyExtractor, Function<O, Double> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Byte2DoubleOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Byte2FloatMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor byte key extractor
	 * @param valueExtractor float value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Byte2FloatMap>
	toByte2FloatMap(Function<O, Byte> keyExtractor, Function<O, Float> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Byte2FloatOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Byte2IntMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor byte key extractor
	 * @param valueExtractor int value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Byte2IntMap>
	toByte2IntMap(Function<O, Byte> keyExtractor, Function<O, Integer> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Byte2IntOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Byte2LongMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor byte key extractor
	 * @param valueExtractor long value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Byte2LongMap>
	toByte2LongMap(Function<O, Byte> keyExtractor, Function<O, Long> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Byte2LongOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Byte2ShortMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor byte key extractor
	 * @param valueExtractor short value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Byte2ShortMap>
	toByte2ShortMap(Function<O, Byte> keyExtractor, Function<O, Short> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Byte2ShortOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Byte2ObjectMap}.
	 *
	 * @param <O> stream element type
	 * @param <T> map value type
	 * @param keyExtractor byte key extractor
	 * @param valueExtractor object value extractor
	 * @return collector
	 */
	public static <O, T> Collector<O, ?, Byte2ObjectMap<T>>
	toByte2ObjectMap(Function<O, Byte> keyExtractor, Function<O, T> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Byte2ObjectOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Char2BooleanMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor char key extractor
	 * @param valueExtractor boolean value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Char2BooleanMap>
	toChar2BooleanMap(Function<O, Character> keyExtractor, Object2BooleanFunction<O> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Char2BooleanOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Char2ByteMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor char key extractor
	 * @param valueExtractor byte value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Char2ByteMap>
	toChar2ByteMap(Function<O, Character> keyExtractor, Function<O, Byte> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Char2ByteOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Char2CharMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor char key extractor
	 * @param valueExtractor char value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Char2CharMap>
	toChar2CharMap(Function<O, Character> keyExtractor, Function<O, Character> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Char2CharOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Char2DoubleMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor char key extractor
	 * @param valueExtractor double value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Char2DoubleMap>
	toChar2DoubleMap(Function<O, Character> keyExtractor, Function<O, Double> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Char2DoubleOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Char2FloatMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor char key extractor
	 * @param valueExtractor float value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Char2FloatMap>
	toChar2FloatMap(Function<O, Character> keyExtractor, Function<O, Float> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Char2FloatOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Char2IntMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor char key extractor
	 * @param valueExtractor int value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Char2IntMap>
	toChar2IntMap(Function<O, Character> keyExtractor, Function<O, Integer> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Char2IntOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Char2LongMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor char key extractor
	 * @param valueExtractor long value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Char2LongMap>
	toChar2LongMap(Function<O, Character> keyExtractor, Function<O, Long> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Char2LongOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Char2ShortMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor char key extractor
	 * @param valueExtractor short value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Char2ShortMap>
	toChar2ShortMap(Function<O, Character> keyExtractor, Function<O, Short> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Char2ShortOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Char2ObjectMap}.
	 *
	 * @param <O> stream element type
	 * @param <T> map value type
	 * @param keyExtractor char key extractor
	 * @param valueExtractor object value extractor
	 * @return collector
	 */
	public static <O, T> Collector<O, ?, Char2ObjectMap<T>>
	toChar2ObjectMap(Function<O, Character> keyExtractor, Function<O, T> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Char2ObjectOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Double2BooleanMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor double key extractor
	 * @param valueExtractor boolean value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Double2BooleanMap>
	toDouble2BooleanMap(Function<O, Double> keyExtractor, Object2BooleanFunction<O> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Double2BooleanOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Double2ByteMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor double key extractor
	 * @param valueExtractor byte value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Double2ByteMap>
	toDouble2ByteMap(Function<O, Double> keyExtractor, Function<O, Byte> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Double2ByteOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Double2CharMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor double key extractor
	 * @param valueExtractor char value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Double2CharMap>
	toDouble2CharMap(Function<O, Double> keyExtractor, Function<O, Character> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Double2CharOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Double2DoubleMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor double key extractor
	 * @param valueExtractor double value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Double2DoubleMap>
	toDouble2DoubleMap(Function<O, Double> keyExtractor, Function<O, Double> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Double2DoubleOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Double2FloatMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor double key extractor
	 * @param valueExtractor float value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Double2FloatMap>
	toDouble2FloatMap(Function<O, Double> keyExtractor, Function<O, Float> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Double2FloatOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Double2IntMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor double key extractor
	 * @param valueExtractor int value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Double2IntMap>
	toDouble2IntMap(Function<O, Double> keyExtractor, Function<O, Integer> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Double2IntOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Double2LongMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor double key extractor
	 * @param valueExtractor long value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Double2LongMap>
	toDouble2LongMap(Function<O, Double> keyExtractor, Function<O, Long> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Double2LongOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Double2ShortMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor double key extractor
	 * @param valueExtractor short value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Double2ShortMap>
	toDouble2ShortMap(Function<O, Double> keyExtractor, Function<O, Short> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Double2ShortOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Double2ObjectMap}.
	 *
	 * @param <O> stream element type
	 * @param <T> map value type
	 * @param keyExtractor double key extractor
	 * @param valueExtractor object value extractor
	 * @return collector
	 */
	public static <O, T> Collector<O, ?, Double2ObjectMap<T>>
	toDouble2ObjectMap(Function<O, Double> keyExtractor, Function<O, T> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Double2ObjectOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Float2BooleanMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor float key extractor
	 * @param valueExtractor boolean value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Float2BooleanMap>
	toFloat2BooleanMap(Function<O, Float> keyExtractor, Object2BooleanFunction<O> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Float2BooleanOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Float2ByteMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor float key extractor
	 * @param valueExtractor byte value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Float2ByteMap>
	toFloat2ByteMap(Function<O, Float> keyExtractor, Function<O, Byte> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Float2ByteOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Float2CharMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor float key extractor
	 * @param valueExtractor char value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Float2CharMap>
	toFloat2CharMap(Function<O, Float> keyExtractor, Function<O, Character> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Float2CharOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Float2DoubleMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor float key extractor
	 * @param valueExtractor double value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Float2DoubleMap>
	toFloat2DoubleMap(Function<O, Float> keyExtractor, Function<O, Double> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Float2DoubleOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Float2FloatMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor float key extractor
	 * @param valueExtractor float value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Float2FloatMap>
	toFloat2FloatMap(Function<O, Float> keyExtractor, Function<O, Float> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Float2FloatOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Float2IntMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor float key extractor
	 * @param valueExtractor int value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Float2IntMap>
	toFloat2IntMap(Function<O, Float> keyExtractor, Function<O, Integer> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Float2IntOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Float2LongMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor float key extractor
	 * @param valueExtractor long value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Float2LongMap>
	toFloat2LongMap(Function<O, Float> keyExtractor, Function<O, Long> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Float2LongOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Float2ShortMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor float key extractor
	 * @param valueExtractor short value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Float2ShortMap>
	toFloat2ShortMap(Function<O, Float> keyExtractor, Function<O, Short> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Float2ShortOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Float2ObjectMap}.
	 *
	 * @param <O> stream element type
	 * @param <T> map value type
	 * @param keyExtractor float key extractor
	 * @param valueExtractor object value extractor
	 * @return collector
	 */
	public static <O, T> Collector<O, ?, Float2ObjectMap<T>>
	toFloat2ObjectMap(Function<O, Float> keyExtractor, Function<O, T> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Float2ObjectOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Long2BooleanMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor long key extractor
	 * @param valueExtractor boolean value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Long2BooleanMap>
	toLong2BooleanMap(Function<O, Long> keyExtractor, Object2BooleanFunction<O> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Long2BooleanOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Long2ByteMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor long key extractor
	 * @param valueExtractor byte value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Long2ByteMap>
	toLong2ByteMap(Function<O, Long> keyExtractor, Function<O, Byte> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Long2ByteOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Long2CharMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor long key extractor
	 * @param valueExtractor char value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Long2CharMap>
	toLong2CharMap(Function<O, Long> keyExtractor, Function<O, Character> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Long2CharOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Long2DoubleMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor long key extractor
	 * @param valueExtractor double value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Long2DoubleMap>
	toLong2DoubleMap(Function<O, Long> keyExtractor, Function<O, Double> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Long2DoubleOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Long2FloatMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor long key extractor
	 * @param valueExtractor float value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Long2FloatMap>
	toLong2FloatMap(Function<O, Long> keyExtractor, Function<O, Float> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Long2FloatOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Long2IntMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor long key extractor
	 * @param valueExtractor int value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Long2IntMap>
	toLong2IntMap(Function<O, Long> keyExtractor, Function<O, Integer> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Long2IntOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Long2LongMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor long key extractor
	 * @param valueExtractor long value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Long2LongMap>
	toLong2LongMap(Function<O, Long> keyExtractor, Function<O, Long> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Long2LongOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Long2ShortMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor long key extractor
	 * @param valueExtractor short value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Long2ShortMap>
	toLong2ShortMap(Function<O, Long> keyExtractor, Function<O, Short> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Long2ShortOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Long2ObjectMap}.
	 *
	 * @param <O> stream element type
	 * @param <T> map value type
	 * @param keyExtractor long key extractor
	 * @param valueExtractor object value extractor
	 * @return collector
	 */
	public static <O, T> Collector<O, ?, Long2ObjectMap<T>>
	toLong2ObjectMap(Function<O, Long> keyExtractor, Function<O, T> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Long2ObjectOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Short2BooleanMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor short key extractor
	 * @param valueExtractor boolean value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Short2BooleanMap>
	toShort2BooleanMap(Function<O, Short> keyExtractor, Object2BooleanFunction<O> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Short2BooleanOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Short2ByteMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor short key extractor
	 * @param valueExtractor byte value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Short2ByteMap>
	toShort2ByteMap(Function<O, Short> keyExtractor, Function<O, Byte> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Short2ByteOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Short2CharMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor short key extractor
	 * @param valueExtractor char value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Short2CharMap>
	toShort2CharMap(Function<O, Short> keyExtractor, Function<O, Character> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Short2CharOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Short2DoubleMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor short key extractor
	 * @param valueExtractor double value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Short2DoubleMap>
	toShort2DoubleMap(Function<O, Short> keyExtractor, Function<O, Double> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Short2DoubleOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Short2FloatMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor short key extractor
	 * @param valueExtractor float value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Short2FloatMap>
	toShort2FloatMap(Function<O, Short> keyExtractor, Function<O, Float> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Short2FloatOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Short2IntMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor short key extractor
	 * @param valueExtractor int value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Short2IntMap>
	toShort2IntMap(Function<O, Short> keyExtractor, Function<O, Integer> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Short2IntOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Short2LongMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor short key extractor
	 * @param valueExtractor long value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Short2LongMap>
	toShort2LongMap(Function<O, Short> keyExtractor, Function<O, Long> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Short2LongOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Short2ShortMap}.
	 *
	 * @param <O> stream element type
	 * @param keyExtractor short key extractor
	 * @param valueExtractor short value extractor
	 * @return collector
	 */
	public static <O> Collector<O, ?, Short2ShortMap>
	toShort2ShortMap(Function<O, Short> keyExtractor, Function<O, Short> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Short2ShortOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building a {@link Short2ObjectMap}.
	 *
	 * @param <O> stream element type
	 * @param <T> map value type
	 * @param keyExtractor short key extractor
	 * @param valueExtractor object value extractor
	 * @return collector
	 */
	public static <O, T> Collector<O, ?, Short2ObjectMap<T>>
	toShort2ObjectMap(Function<O, Short> keyExtractor, Function<O, T> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Short2ObjectOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building an {@link Object2BooleanMap}.
	 *
	 * @param <O> stream element type
	 * @param <K> map key type
	 * @param keyExtractor object key extractor
	 * @param valueExtractor boolean value extractor
	 * @return collector
	 */
	public static <O, K> Collector<O, ?, Object2BooleanMap<K>>
	toObject2BooleanMap(Object2ObjectFunction<O, K> keyExtractor, Object2BooleanFunction<O> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Object2BooleanOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building an {@link Object2ByteMap}.
	 *
	 * @param <O> stream element type
	 * @param <K> map key type
	 * @param keyExtractor object key extractor
	 * @param valueExtractor byte value extractor
	 * @return collector
	 */
	public static <O, K> Collector<O, ?, Object2ByteMap<K>>
	toObject2ByteMap(Object2ObjectFunction<O, K> keyExtractor, Function<O, Byte> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Object2ByteOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building an {@link Object2CharMap}.
	 *
	 * @param <O> stream element type
	 * @param <K> map key type
	 * @param keyExtractor object key extractor
	 * @param valueExtractor char value extractor
	 * @return collector
	 */
	public static <O, K> Collector<O, ?, Object2CharMap<K>>
	toObject2CharMap(Object2ObjectFunction<O, K> keyExtractor, Function<O, Character> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Object2CharOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building an {@link Object2DoubleMap}.
	 *
	 * @param <O> stream element type
	 * @param <K> map key type
	 * @param keyExtractor object key extractor
	 * @param valueExtractor double value extractor
	 * @return collector
	 */
	public static <O, K> Collector<O, ?, Object2DoubleMap<K>>
	toObject2DoubleMap(Object2ObjectFunction<O, K> keyExtractor, Function<O, Double> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Object2DoubleOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building an {@link Object2FloatMap}.
	 *
	 * @param <O> stream element type
	 * @param <K> map key type
	 * @param keyExtractor object key extractor
	 * @param valueExtractor float value extractor
	 * @return collector
	 */
	public static <O, K> Collector<O, ?, Object2FloatMap<K>>
	toObject2FloatMap(Object2ObjectFunction<O, K> keyExtractor, Function<O, Float> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Object2FloatOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building an {@link Object2IntMap}.
	 *
	 * @param <O> stream element type
	 * @param <K> map key type
	 * @param keyExtractor object key extractor
	 * @param valueExtractor int value extractor
	 * @return collector
	 */
	public static <O, K> Collector<O, ?, Object2IntMap<K>>
	toObject2IntMap(Object2ObjectFunction<O, K> keyExtractor, Function<O, Integer> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Object2IntOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building an {@link Object2LongMap}.
	 *
	 * @param <O> stream element type
	 * @param <K> map key type
	 * @param keyExtractor object key extractor
	 * @param valueExtractor long value extractor
	 * @return collector
	 */
	public static <O, K> Collector<O, ?, Object2LongMap<K>>
	toObject2LongMap(Object2ObjectFunction<O, K> keyExtractor, Function<O, Long> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Object2LongOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building an {@link Object2ShortMap}.
	 *
	 * @param <O> stream element type
	 * @param <K> map key type
	 * @param keyExtractor object key extractor
	 * @param valueExtractor short value extractor
	 * @return collector
	 */
	public static <O, K> Collector<O, ?, Object2ShortMap<K>>
	toObject2ShortMap(Object2ObjectFunction<O, K> keyExtractor, Function<O, Short> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Object2ShortOpenHashMap::new, keyExtractor, valueExtractor);
	}

	/**
	 * Creates a collector for building an {@link Object2ObjectMap}.
	 *
	 * @param <O> stream element type
	 * @param <K> map key type
	 * @param <T> map value type
	 * @param keyExtractor object key extractor
	 * @param valueExtractor object value extractor
	 * @return collector
	 */
	public static <O, K, T> Collector<O, ?, Object2ObjectMap<K, T>>
	toObject2ObjectMap(Object2ObjectFunction<O, K> keyExtractor, Function<O, T> valueExtractor) {
		return new FastUtilMapCollectorImpl<>(Object2ObjectOpenHashMap::new, keyExtractor, valueExtractor);
	}

	private FastUtilCollectors() {
		throw new UnsupportedOperationException("No instances.");
	}

	private static IllegalStateException duplicateElementException(Object v) {
		return new IllegalStateException(String.format("Duplicate element %s", v));
	}
}
