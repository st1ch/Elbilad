package inc.itnity.elbilad.domain.usecases;

import inc.itnity.elbilad.data.repositories.ElbiladRepository;
import inc.itnity.elbilad.domain.models.Journal;
import inc.itnity.elbilad.domain.schedulers.ObserveOn;
import inc.itnity.elbilad.domain.schedulers.SubscribeOn;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by st1ch on 18.02.17.
 */

public class DownloadJournalUseCase extends UseCase<String> {

  private ElbiladRepository elbiladRepository;

  private Journal journal;

  @Inject DownloadJournalUseCase(SubscribeOn subscribeOn, ObserveOn observeOn,
      ElbiladRepository elbiladRepository) {
    super(subscribeOn, observeOn);
    this.elbiladRepository = elbiladRepository;
  }

  public void setJournal(Journal journal) {
    this.journal = journal;
  }

  @Override protected Observable<String> getUseCaseObservable() {
    return elbiladRepository.downloadJournal(journal.getUrlPath(), journal.getFilename());
  }
}
