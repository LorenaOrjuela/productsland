package com.orvi.productsland.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.orvi.productsland.domain.usecase.RefreshProductsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ProductSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val refreshProductsUseCase: RefreshProductsUseCase
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            refreshProductsUseCase()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
