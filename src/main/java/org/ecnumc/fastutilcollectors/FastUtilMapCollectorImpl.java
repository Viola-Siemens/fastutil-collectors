package org.ecnumc.fastutilcollectors;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static org.ecnumc.fastutilcollectors.FastUtilCollectors.CH_ID;

/**
 * A {@link Collector} implementation for building {@link Map} instances.
 * @param <T> element type
 * @param <K> key type
 * @param <V> value type
 * @param <A> map type
 * @author liudongyu
 */
public final class FastUtilMapCollectorImpl<T, K, V, A extends Map<K, V>> implements Collector<T, A, A> {
	private final Supplier<A> supplier;
	private final Function<? super T, ? extends K> keyMapper;
	private final Function<? super T, ? extends V> valueMapper;

	public FastUtilMapCollectorImpl(Supplier<A> supplier, Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper) {
		this.supplier = supplier;
		this.keyMapper = keyMapper;
		this.valueMapper = valueMapper;
	}
	@Override
	public Supplier<A> supplier() {
		return this.supplier;
	}

	@Override
	public BiConsumer<A, T> accumulator() {
		return (a, e) -> {
			K k = this.keyMapper.apply(e);
			V v = this.valueMapper.apply(e);
			V u = a.putIfAbsent(k, v);
			if (u != null) {
				throw duplicateKeyException(k, u, v);
			}
		};
	}

	@Override
	public BinaryOperator<A> combiner() {
		return (a, b) -> {
			for (Map.Entry<K,V> e : b.entrySet()) {
				K k = e.getKey();
				V v = Objects.requireNonNull(e.getValue());
				V u = a.putIfAbsent(k, v);
				if (u != null) {
					throw duplicateKeyException(k, u, v);
				}
			}
			return a;
		};
	}

	@Override
	public Function<A, A> finisher() {
		return Function.identity();
	}

	@Override
	public Set<Characteristics> characteristics() {
		return CH_ID;
	}

	private static IllegalStateException duplicateKeyException(Object k, Object u, Object v) {
		return new IllegalStateException(String.format("Duplicate key %s (attempted merging values %s and %s)", k, u, v));
	}
}
