package com.example.newappdi.newapp.DI.modules;

import com.example.newappdi.NewsApp.Repository.API_Interface;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NetworkingModule_GetRetrofitServiceFactory implements Factory<API_Interface> {
  private final NetworkingModule module;

  private final Provider<Retrofit> clientrProvider;

  public NetworkingModule_GetRetrofitServiceFactory(NetworkingModule module,
      Provider<Retrofit> clientrProvider) {
    this.module = module;
    this.clientrProvider = clientrProvider;
  }

  @Override
  public API_Interface get() {
    return getRetrofitService(module, clientrProvider.get());
  }

  public static NetworkingModule_GetRetrofitServiceFactory create(NetworkingModule module,
      Provider<Retrofit> clientrProvider) {
    return new NetworkingModule_GetRetrofitServiceFactory(module, clientrProvider);
  }

  public static API_Interface getRetrofitService(NetworkingModule instance, Retrofit clientr) {
    return Preconditions.checkNotNullFromProvides(instance.getRetrofitService(clientr));
  }
}
