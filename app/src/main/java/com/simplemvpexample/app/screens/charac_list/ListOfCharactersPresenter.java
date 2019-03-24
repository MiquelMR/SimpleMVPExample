package com.simplemvpexample.app.screens.charac_list;

import com.simplemvpexample.app.data.model.CustomCharacter;
import com.simplemvpexample.app.screens.charac_list.interfaces.I_ListOfCAdapter;
import com.simplemvpexample.app.screens.charac_list.interfaces.I_ListOfCInteractor;
import com.simplemvpexample.app.screens.charac_list.interfaces.I_ListOfCPresenter;
import com.simplemvpexample.app.screens.charac_list.interfaces.I_ListOfCView;

import java.util.List;

import javax.inject.Inject;

public class ListOfCharactersPresenter implements I_ListOfCPresenter {

    private I_ListOfCView view;
    private I_ListOfCAdapter adapter;
    private I_ListOfCInteractor interactor;

    @Inject
    public ListOfCharactersPresenter(I_ListOfCInteractor interactor) {
        this.interactor = interactor;
        this.interactor.onAttach( this );
    }

    @Override
    public void characterRemoved(int index) {

        adapter.characterRemoved( index );

        if (!interactor.hasCharacters()) {
            view.showNoCharacters();
        }
    }

    @Override
    public void onAttach(I_ListOfCView view) {
        this.view = view;
    }

    @Override
    public void characterAdded(int index) {

        adapter.characterInserted( index );

        view.hideNoCharacters();
    }

    @Override
    public void characterUpdated(int index) {
        adapter.characterUpdated( index );
    }

    @Override
    public void onCharactersAvailable(List<CustomCharacter> characters) {
        if (!characters.isEmpty()) {
            view.hideNoCharacters();
        }

        adapter.setCharacters( characters );
    }

    @Override
    public void startPresenter() {
        interactor.getCharacters();
    }

    @Override
    public void onViewDestroy() {
        adapter.onDetach();
        adapter = null;
        interactor.onDetach();
        view = null;
    }

    @Override
    public void attachAdapter(I_ListOfCAdapter adapter) {
        this.adapter = adapter;
        this.adapter.onAttachPresenter( this );
    }

    @Override
    public void onCharacterSelected(CustomCharacter character) {
        view.viewCharacterDetails( character );
    }
}