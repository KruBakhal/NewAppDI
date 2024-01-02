package com.example.newappdi.newapp.DI.modules;

import com.androiddevs.mvvmnewsapp.db.ArticleDao;
import com.androiddevs.mvvmnewsapp.db.ArticleDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class NetworkingModule_ProvideChannelDaoFactory implements Factory<ArticleDao> {
  private final NetworkingModule module;

  private final Provider<ArticleDatabase> appDatabaseProvider;

  public NetworkingModule_ProvideChannelDaoFactory(NetworkingModule module,
      Provider<ArticleDatabase> appDatabaseProvider) {
    this.module = module;
    this.appDatabaseProvider = appDatabaseProvider;
  }

  @Override
  public ArticleDao get() {
    return provideChannelDao(module, appDatabaseProvider.get());
  }

  public static NetworkingModule_ProvideChannelDaoFactory create(NetworkingModule module,
      Provider<ArticleDatabase> appDatabaseProvider) {
    return new NetworkingModule_ProvideChannelDaoFactory(module, appDatabaseProvider);
  }

  public static ArticleDao provideChannelDao(NetworkingModule instance,
      ArticleDatabase appDatabase) {
    return Preconditions.checkNotNullFromProvides(instance.provideChannelDao(appDatabase));
  }
}
