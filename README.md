# 🏆 Competitive Programming Notebook

![C++](https://img.shields.io/badge/C%2B%2B-00599C?style=flat&logo=c%2B%2B&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Python](https://img.shields.io/badge/Python-3776AB?style=flat&logo=python&logoColor=white)
![LaTeX](https://img.shields.io/badge/LaTeX-008080?style=flat&logo=latex&logoColor=white)
![Pages](https://img.shields.io/badge/notebook-330%20pages-blueviolet)
![Status](https://img.shields.io/badge/status-in%20progress-brightgreen)
![Updates](https://img.shields.io/badge/updates-constant-orange)

My personal competitive programming toolkit: a **330-page C++ notebook (PDF)** with algorithms, data structures and ready-to-paste templates, plus a **code repository** of solutions organized by topic in C++, Java and Python.

> 📖 The notebook is written in **Spanish**. Code identifiers and technical terms are kept in English where that is the standard in contests.

> 🔄 **This repository is updated constantly.** The notebook and the code are refined continuously: explanations get rewritten, templates are optimized, typos and duplicated sections are fixed, and new chapters are added. Expect changes between versions.

---

## 👋 About

Hi, I'm **Juan Carlos Morales** — a student at **Universidad del Bosque** (Bogotá, Colombia), currently in my 4th semester, pursuing a double degree in **Software Engineering** and **Robotics Engineering**. I compete in ICPC-style contests with my university team and use this repository as my personal notebook: templates, patterns and solved problems collected while practicing.

---

## 📘 The Notebook (PDF)

**[⬇️ Open the Competitive Programming Notebook](./notebook/competitive-programming-notebook.pdf)**

| | |
|---|---|
| **Title** | Competitive Programming Notebook — Algoritmos, estructuras de datos y plantillas |
| **Language of the code** | C++ |
| **Written in** | Spanish |
| **Length** | 330 pages (A4) |
| **Structure** | 11 parts · 66 chapters |
| **Typeset with** | LaTeX |
| **Year** | 2026 |

### What you'll find inside

Each topic is explained briefly and followed by a template you can drop straight into a submission. Chapters include code listings with line numbers, comparison tables, complexity notes, warnings for common traps (marked with `!`) and short "rules of thumb" on when to use each technique.

| Part | Topic | Chapters | Highlights | Pages |
|:---:|---|:---:|---|:---:|
| **I** | Introduction | 1 | Fast I/O in three tiers (`cin`, `sync_with_stdio`, `fread` byte reader), `printf` formatting, parsing and casting | 9 |
| **II** | Data Structures | 2–5 | Vectors, stacks, queues, heaps, comparators, hash/tree sets and maps, **range queries**: prefix sums, Fenwick (BIT), sqrt decomposition, sparse table, segment tree, Mo's algorithm, wavelet tree, HLD | 11 |
| **III** | Graphs | 6–24 | BFS/DFS, connectivity (bridges, articulation points, biconnected components), topological sort, **shortest paths** (0-1 BFS, Dijkstra, Bellman-Ford, Floyd-Warshall), **MST** (Kruskal, Prim), SCC (Kosaraju, Tarjan), bipartite graphs, trees (diameter, center, tree DP, rerooting), **LCA**, tree queries, DSU (rollback, offline), Eulerian paths, **network flow** (Dinic, min-cost max-flow), 2-SAT, backtracking, centroid decomposition, DSU on tree, Hopcroft-Karp | 32 |
| **IV** | Sorting & Searching | 25–27 | Two pointers, sliding window, ternary/exponential/jump search, meet in the middle, merge sort and inversions, QuickSelect, **binary search** on arrays, on answer and on reals | 107 |
| **V** | Computational Geometry | 28–34 | Line sweep (segment intersection, rectangle union, skyline, closest pair), geometric primitives (dot/cross product, CCW, distances, rotations), triangles, circles, polygons (shoelace, ray casting, Pick's theorem), convex hull (Jarvis, Andrew), geometric ad-hoc counting | 125 |
| **VI** | Dynamic Programming | 35–39 | Memoization vs. tabulation, knapsack variants, coin change, sequence DP (LIS/LCS), interval DP, box stacking | 156 |
| **VII** | Greedy | 40–46 | Exchange argument, greedy vs. DP, interval scheduling, job sequencing, Huffman, heap-based greedy, monotonic stack, when coin-change greedy works | 174 |
| **VIII** | Ad-hoc & Simulation | 47–53 | Simulation templates, cycle detection, Josephus, event-driven simulation, cellular automata, grids and matrices, dates and time, BigInt and exact fractions, expression parsing (shunting-yard, recursive descent), coordinate compression, invariants, constructive algorithms, **stress testing** and interactive problems | 193 |
| **IX** | String Processing | 54–60 | Trie, rolling hash, prefix function, suffix array + Kasai, suffix automaton, KMP, Z-algorithm, Rabin-Karp, Boyer-Moore, Aho-Corasick, palindromes (Eertree), LCS/LIS, edit distance, Booth, Lyndon words, anagrams | 245 |
| **X** | Bitwise | — | Binary representation, tricks and GCC built-ins, `std::bitset`, submask enumeration, SOS DP, bitmask DP, broken-profile DP, XOR basis, binary trie, Gray code, Walsh-Hadamard, Möbius/zeta transforms | 278 |
| **XI** | Game Theory | 61–66 | Win/lose states, Nim and Sprague-Grundy, subtraction games, Staircase Nim, Wythoff, games on graphs and trees, partizan games, minimax with memoization, alpha-beta pruning | 300 |

### Conventions used in the notebook

- **C++ style:** `typedef long long ll` and `rep`-style loop macros to keep templates short.
- **Fast I/O:** Tier 2 (`sync_with_stdio(false)` + `cin.tie(nullptr)`) is the default; the `fread` reader is reserved for inputs on the order of 10⁶–10⁷ tokens.
- **Output:** always `"\n"`, never `endl`.

---

## 📂 Repository Layout

Solutions and templates live under `algorithms/`, split first by language, then by topic:

```
.
├── notebook/
│   └── competitive-programming-notebook.pdf
└── algorithms/
    ├── cpp/
    │   ├── graphs/
    │   ├── dynamic-programming/
    │   ├── strings/
    │   ├── ad-hoc/
    │   ├── greedy/
    │   ├── number-theory/
    │   ├── data-structures/
    │   ├── trees/
    │   ├── binary-search/
    │   ├── geometry/
    │   ├── combinatorics/
    │   ├── bitmasking/
    │   ├── disjoint-set-union/
    │   ├── sorting-searching/
    │   └── math/
    ├── java/
    │   └── (same 15 topic folders)
    └── python/
        └── (same 15 topic folders)
```

15 topics × 3 languages = **45 folders**.

### Topics ↔ folders

| Topic | Folder | In the PDF |
|---|---|:---:|
| Graph algorithms | `graphs` | ✅ Part III |
| Trees | `trees` | ✅ Part III (ch. 14–16) |
| Disjoint set union (DSU) | `disjoint-set-union` | ✅ Part III (ch. 17) |
| Data structures | `data-structures` | ✅ Part II |
| Sorting & searching | `sorting-searching` | ✅ Part IV |
| Binary search | `binary-search` | ✅ Part IV (ch. 27) |
| Computational geometry | `geometry` | ✅ Part V |
| Dynamic programming | `dynamic-programming` | ✅ Part VI |
| Greedy algorithms | `greedy` | ✅ Part VII |
| Ad-hoc / simulation | `ad-hoc` | ✅ Part VIII |
| String algorithms | `strings` | ✅ Part IX |
| Bitmasking | `bitmasking` | ✅ Part X |
| Number theory | `number-theory` | 🚧 planned |
| Combinatorics | `combinatorics` | 🚧 planned |
| Math | `math` | 🚧 planned |

---

## 🚀 Usage

1. **Need theory or a quick refresher?** Open the notebook PDF and jump to the part you need using the table of contents.
2. **Need code?** Browse into `algorithms/<language>/<topic>/` for annotated solutions and templates.
3. **Contest day:** copy the C++ template you need (Fast I/O, DSU, segment tree, Dijkstra, …) and adapt it.

---

## 🗺️ Roadmap

- [ ] Add number theory, combinatorics and modular arithmetic chapters to the notebook
- [ ] Fill in the remaining sections of Part XI (Game Theory, ch. 66)
- [ ] Port notebook templates to the Java and Python folders
- [ ] Link each template to practice problems (CSES, Codeforces, UVa, ICPC Gym)

---

## 📌 Notes

This is a living repository, updated constantly to refine details — folders will fill in over time as new problems are solved and templates are improved.

Found a bug or a better approach? Feel free to open an issue.
