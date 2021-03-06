package com.example.imedical.core.di.component

import com.example.imedical.AndroidApplication
import com.example.imedical.cart.presentation.view.fragment.CartFragment
import com.example.imedical.core.di.module.ApplicationModule
import com.example.imedical.core.di.module.LoginModule
import com.example.imedical.core.di.module.ProductModule
import com.example.imedical.core.di.module.RegistrationModule
import com.example.imedical.addresses.presentation.view.fragment.AddressesFragment
import com.example.imedical.categories.presentation.view.activity.CategoriesActivity
import com.example.imedical.compare.presentation.view.fragment.CompareListFragment
import com.example.imedical.core.di.module.*
import com.example.imedical.core.platform.BaseActivity
import com.example.imedical.forgetpassword.forget.presentation.activity.ForgetPasswordActivity
import com.example.imedical.forgetpassword.resetpassword.presentation.activity.ResetPasswordActivity
import com.example.imedical.forgetpassword.verify.presentation.activity.VerifyPasswordActivity
import com.example.imedical.home.presentation.view.fragment.BestSellersFragment
import com.example.imedical.home.presentation.view.fragment.NavigationFragment
import com.example.imedical.home.presentation.view.activity.HomeActivity
import com.example.imedical.home.presentation.view.activity.ProductDetailsActivity
import com.example.imedical.home.presentation.view.fragment.OffersFragment
import com.example.imedical.login.presentation.view.activity.LoginActivity
import com.example.imedical.registration.presentation.fragment.RegistrationFragment
import com.example.imedical.shop.presentation.view.activity.FilterShopActivity
import com.example.imedical.shop.presentation.view.fragment.ShopFragment
import com.example.imedical.verification.presentation.fragment.VerificationFragment
import com.example.imedical.wishlist.presentation.view.fragment.WishListFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */
@Singleton
@Component(modules = [ApplicationModule::class,
    LoginModule::class,
    RegistrationModule::class,
    ProductModule::class,
    VerificationModule::class,
    ForgetPasswordModule::class,
    ResetPasswordModule::class,
    WishListModule::class,
    CartModule::class,
    ResetPasswordModule::class,
    CompareListModule::class,
    AddressesModule::class,
    CategoriesModule::class,
    ShopModule::class]) interface ApplicationComponent {

    fun inject(baseActivity: BaseActivity)
    fun inject(homeActivity: HomeActivity)
    fun inject(application: AndroidApplication)
    fun inject(loginActivity: LoginActivity)
    fun inject(registrationFragment: RegistrationFragment)
    fun inject(bestSellersFragment: BestSellersFragment)
    fun inject(navigationFragment: NavigationFragment)
    fun inject(verificationFragment: VerificationFragment)
    fun inject(forgetPasswordActivity: ForgetPasswordActivity)
    fun inject(verifyPasswordActivity: VerifyPasswordActivity)
    fun inject(resetPasswordActivity: ResetPasswordActivity)
    fun inject(offersFragment: OffersFragment)
    fun inject(wishListFragment: WishListFragment)
    fun inject(cartFragment: CartFragment)
    fun inject(compareListFragment: CompareListFragment)
    fun inject(addressesFragment: AddressesFragment)
    fun inject(categoriesActivity: CategoriesActivity)
    fun inject(shopFragment: ShopFragment)
    fun inject(productDetailsActivity: ProductDetailsActivity)
    fun inject(filterShopActivity: FilterShopActivity)
}