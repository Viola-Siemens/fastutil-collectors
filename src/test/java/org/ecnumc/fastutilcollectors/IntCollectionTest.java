package org.ecnumc.fastutilcollectors;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.ints.*;
import it.unimi.dsi.fastutil.objects.ObjectList;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntCollectionTest {
	@Test
	void testIntStreamCollect() {
		List<Integer> list = IntList.of(1, 2, 3, 2);
		IntList result = list.stream().collect(FastUtilCollectors.toIntCollection(IntArrayList::new, Function.identity()));
		assertEquals(list, result);
	}

	@Test
	void testIntStreamCollectUnique() {
		List<Integer> list = IntList.of(1, 2, 3);
		IntList result = list.stream().collect(FastUtilCollectors.toIntCollection(IntArrayList::new, Function.identity()));
		assertEquals(list, result);
	}

	@Test
	void testIntStreamCollectUnique_throws() {
		Stream<Integer> stream = Stream.of(1, 2, 3, 2);
		assertThrows(IllegalStateException.class, () -> stream.collect(FastUtilCollectors.toUniqueIntCollection(IntOpenHashSet::new, Function.identity())));
	}

	@Test
	void testIntStreamCollectUnique_empty() {
		Stream<Integer> stream = Stream.empty();
		IntList result = stream.collect(FastUtilCollectors.toUniqueIntCollection(IntArrayList::new, Function.identity()));
		assertEquals(IntList.of(), result);
	}

	@Test
	void testIntStreamCollectUnique_null() {
		Stream<Integer> stream = Stream.of(new Integer[]{null});
		assertThrows(NullPointerException.class, () -> stream.collect(FastUtilCollectors.toUniqueIntCollection(IntArrayList::new, Function.identity())));
	}

	@Test
	void testObject2IntMapCollect() {
		List<Pair<String, Integer>> list = ObjectList.of(Pair.of("a", 1), Pair.of("b", 2), Pair.of("c", 3));
		Int2CharMap result = list.stream().collect(
				FastUtilCollectors.toInt2CharMap(Pair::right, p -> p.left().charAt(0))
		);
		Int2CharMap expected = new Int2CharOpenHashMap();
		expected.put(1, 'a');
		expected.put(2, 'b');
		expected.put(3, 'c');
		assertEquals(expected, result);
	}
}
