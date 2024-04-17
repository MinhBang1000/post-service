package ctu.cit.se.generalinformation.migrations;

import java.util.List;

public interface IBaseInitData<T> {
    List<T> getInitData();
}
