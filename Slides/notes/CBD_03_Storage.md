# CBD_03_Storage

---

## RDMS – Big Picture

---

#### **1. Heap**
A **Heap** in the context of RDBMS is a simple, flat structure used for storing data in no specific order. It’s essentially a collection of rows appended into pages and files.

- **Structure**: 
  - Each row of data is stored with identifiers like `File_ID`, `Page_ID`, and `Row_ID`:
    - **File_ID**: Identifies the physical file where the data is stored.
    - **Page_ID**: Points to a specific page (a fixed-size block) in the file.
    - **Row_ID**: Locates the exact row within the page.
  - Data is written as it comes (appended), with no sorting or ordering.

- **Usage**:
  - A heap is efficient for **inserting data quickly**, but it’s slower for searching or retrieving specific rows unless an index is added.

- **Example**:
  Imagine a stack of unsorted papers (your data). Each paper is added to the stack (append) without worrying about its position.

---

#### **2. B-Tree**
A **B-Tree** is a data structure commonly used for indexing in RDBMS. Indexes are like shortcuts, helping you quickly find specific rows without scanning the entire heap.

- **Secondary Indexes**:
  - These indexes are built on columns that are not the primary key.
  - Useful when searching for data based on non-primary columns.
  - Example: If you have a table of books, a secondary index might let you quickly search by `Author` even if the primary key is `Book_ID`.

- **Clustered Index**:
  - A special type of index where the data in the table is physically stored in the order of the index.
  - Faster for range queries or sorting because the data is already ordered.
  - Example: A table sorted by `LastName` means searching for names starting with "S" is very efficient.

---

#### **3. Transaction Log**
The **Transaction Log** is like a journal that keeps a record of all database operations. It’s **append-only**, meaning new entries are always added to the end.

- **Purpose**:
  - Ensures durability and consistency (part of ACID properties).
  - Allows the database to recover from crashes by replaying or rolling back logged operations.

- **How it works**:
  - Every change (insert, update, delete) is recorded in the transaction log before it’s applied to the actual data.
  - If a failure occurs, the log is used to restore the database to its last consistent state.

- **Analogy**:
  Imagine writing down every move you make in a game. If the game crashes, you can use your notes to restart from where you left off.

---

#### **How They Work Together**
1. **Heap**: Stores the raw data in files and pages.
   - Example: When you insert a new row, it gets appended to the appropriate page.

2. **B-Tree**: Provides indexes for quick lookups and sorting.
   - Example: When you search for a specific row, the B-Tree index helps find it without scanning the entire heap.

3. **Transaction Log**: Records every operation to ensure durability and recoverability.
   - Example: If you delete a row, the action is logged. If the system crashes mid-operation, the log ensures the database can roll back or complete the deletion.

---

This combination ensures that an RDBMS is:
- **Efficient** at handling large datasets.
- **Reliable** in maintaining data consistency.
- **Fast** at retrieving specific information.

## NoSQL Engines – Big Picture

---

#### Key Components:
1. **Commit Log (Write-Ahead Log)**:
   - When a write operation occurs, the data is first recorded in the **commit log**, which is stored on disk. 
   - Purpose: Ensures **durability**. If a system crash occurs, the log can be replayed to recover data.
   - Acts as a sequential log of all changes, making disk writes efficient.

2. **MemTable**:
   - Data is then written to an in-memory data structure called the **MemTable**.
   - The MemTable keeps key-value pairs sorted in memory for fast access.
   - Once the MemTable reaches a certain size, it is marked as **immutable** and written to disk as an SSTable.

3. **SSTable (Sorted String Table)**:
   - Immutable files written to disk from the MemTable.
   - These files are sorted and stored as a sequence of key-value pairs for efficient lookups.
   - Multiple SSTables can exist, requiring a **compaction procedure**.

4. **Compaction Procedure**:
   - Over time, multiple SSTables accumulate. Compaction merges these SSTables to:
     - Remove duplicate keys.
     - Reclaim space by discarding obsolete data.
     - Maintain the sorted order.
   - Improves read performance by reducing the number of files that need to be searched.

5. **Indexing (e.g., B-tree)**:
   - To facilitate fast lookups, indexes like **B-trees** or bloom filters are used to locate the relevant SSTables or key-value pairs within them.

---

#### How the Process Works (Based on the Diagrams):
1. **Write Path**:
   - Data enters through the write operation.
   - Written to the **commit log** for durability.
   - Simultaneously written to the **MemTable**, which resides in memory for fast access.

2. **Flush to Disk**:
   - When the MemTable reaches a threshold size, it is converted to an **Immutable MemTable**.
   - The Immutable MemTable is flushed to disk as an **SSTable**.

3. **Compaction**:
   - Multiple SSTables are periodically compacted into fewer SSTables.
   - Compaction reduces redundancy and optimizes storage.

4. **Read Path**:
   - A read query first checks the **MemTable** (in memory).
   - If the key is not found in the MemTable, the query checks the **SSTables** on disk.
   - Index structures like B-trees help locate the relevant SSTables quickly.

---

#### Advantages:
- **Write Optimization**: Sequential writes to the commit log and SSTables reduce random disk I/O, which is slower.
- **Efficient Reads**: Sorted SSTables and indexing improve query performance.
- **Scalability**: LSM Trees handle large-scale data efficiently by distributing compaction and storage across multiple SSTables.

---

#### Diagram Breakdown:
1. **Top Diagram**:
   - Illustrates the process from writing data to disk.
   - Highlights how data flows from memory (MemTable) to persistent storage (SSTable) via the commit log.

2. **Bottom Diagram**:
   - Provides a detailed breakdown of the **compaction process**.
   - Shows how SSTables are merged and indexed into a global B+-Tree for efficient data retrieval.

In summary, the LSM Tree is a foundational concept in many NoSQL databases (e.g., Cassandra, LevelDB, RocksDB) for balancing high write throughput with read efficiency, while ensuring data durability and scalability.