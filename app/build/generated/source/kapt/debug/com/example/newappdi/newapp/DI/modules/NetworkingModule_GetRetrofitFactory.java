package com.example.newappdi.newapp.DI.modules;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
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
public final class NetworkingModule_GetRetrofitFactory implements Factory<Retrofit> {
  private final NetworkingModule module;

  public NetworkingModule_GetRetrofitFactory(NetworkingModule module) {
    this.module = module;
  }

  @Override
  public Retrofit get() {
    return getRetrofit(module);
  }

  public static NetworkingModule_GetRetrofitFactory create(NetworkingModule module) {
    return new NetworkingModule_GetRetrofitFactory(module);
  }

  public static Retrofit getRetrofit(NetworkingModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.getRetrofit());
  }
}
