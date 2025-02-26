package dev.luaoctaviano.dindin.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.luaoctaviano.dindin.core.data.source.local.entity.Transaction
import dev.luaoctaviano.dindin.core.util.enums.TransactionType
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(item: Transaction)

    @Query("DELETE FROM `Transaction` WHERE id = :transactionId")
    suspend fun delete(transactionId: Long)

    @Query("SELECT count(*) FROM 'Transaction'")
    suspend fun count(): Int

    @Query(
        """
        SELECT 
        'Transaction'.id,
        'Transaction'.description,
        'Transaction'.amount,
        BankAccount.name AS accountName,
        Category.name AS categoryName,
        Category.icon AS categoryIcon,
        'Transaction'.type,
        'Transaction'.createdAt,
        'Transaction'.dueDate,
        'Transaction'.isPaid
        FROM 'Transaction'
        JOIN BankAccount ON 'Transaction'.accountId = BankAccount.id
        JOIN Category ON 'Transaction'.categoryId = Category.id
        ORDER BY dueDate DESC
        """
    )
    fun getAllAsFlow(): Flow<List<DetailedTransaction>>

    @Query(
        """
        SELECT 
        'Transaction'.id,
        'Transaction'.description,
        'Transaction'.amount,
        BankAccount.name AS accountName,
        Category.name AS categoryName,
        Category.icon AS categoryIcon,
        'Transaction'.type,
        'Transaction'.createdAt,
        'Transaction'.dueDate,
        'Transaction'.isPaid
        FROM 'Transaction'
        JOIN BankAccount ON 'Transaction'.accountId = BankAccount.id
        JOIN Category ON 'Transaction'.categoryId = Category.id
        ORDER BY createdAt DESC LIMIT 5
        """
    )
    fun getLastFiveAsFlow(): Flow<List<DetailedTransaction>>

    @Query(
        """SELECT
            COALESCE(
                SUM(transactionSum.sumValue),
                0
            ) + COALESCE(
                SUM(BankAccount.initialBalance),
                0
            ) AS totalAmount
        FROM
            BankAccount
        LEFT JOIN (
            SELECT
                accountId,
                SUM(amount) AS sumValue
            FROM
                'Transaction'
            WHERE
                isPaid = 1
            AND strftime(
                '%Y-%m',
                datetime(dueDate / 1000, 'unixepoch')
            ) <= strftime(
                '%Y-%m',
                datetime(:dateInMillis / 1000, 'unixepoch')
            )
            GROUP BY
                accountId
        ) AS transactionSum
        ON BankAccount.id = transactionSum.accountId;
        """
    )
    fun getTotalBalance(dateInMillis: Long): Flow<Long>
}

data class DetailedTransaction(
    val id: Long,
    val description: String,
    val amount: Long,
    val accountName: String,
    val categoryName: String,
    val categoryIcon: String,
    val type: TransactionType,
    val createdAt: Date,
    val dueDate: Date,
    val isPaid: Boolean,
)