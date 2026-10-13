package com.orvi.productsland.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orvi.productsland.domain.model.Product
import com.orvi.productsland.domain.usecase.GetProductsUseCase
import com.orvi.productsland.domain.usecase.RefreshProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProductListUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val refreshProductsUseCase: RefreshProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductListUiState(isLoading = true))
    val uiState: StateFlow<ProductListUiState> = _uiState

    init {
        viewModelScope.launch {
            getProductsUseCase().collect { products ->
                _uiState.update { it.copy(products = products) }
            }
        }
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                refreshProductsUseCase()
                _uiState.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { current ->
                    current.copy(
                        isLoading = false,
                        errorMessage = if (current.products.isEmpty()) {
                            "No se pudieron cargar los productos. Verifica tu conexión."
                        } else {
                            null
                        }
                    )
                }
            }
        }
    }
}
