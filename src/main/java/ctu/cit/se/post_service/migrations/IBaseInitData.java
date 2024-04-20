package ctu.cit.se.post_service.migrations;

import java.util.List;

public interface IBaseInitData<T> {
    List<T> getInitData();
}
