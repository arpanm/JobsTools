import axios from 'axios';
import {
  parseHeaderForLinks,
  loadMoreDataWhenScrolled,
  ICrudGetAction,
  ICrudGetAllAction,
  ICrudPutAction,
  ICrudDeleteAction,
} from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IQCashUploadRow, defaultValue } from 'app/shared/model/q-cash-upload-row.model';

export const ACTION_TYPES = {
  FETCH_QCASHUPLOADROW_LIST: 'qCashUploadRow/FETCH_QCASHUPLOADROW_LIST',
  FETCH_QCASHUPLOADROW: 'qCashUploadRow/FETCH_QCASHUPLOADROW',
  CREATE_QCASHUPLOADROW: 'qCashUploadRow/CREATE_QCASHUPLOADROW',
  UPDATE_QCASHUPLOADROW: 'qCashUploadRow/UPDATE_QCASHUPLOADROW',
  DELETE_QCASHUPLOADROW: 'qCashUploadRow/DELETE_QCASHUPLOADROW',
  RESET: 'qCashUploadRow/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IQCashUploadRow>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type QCashUploadRowState = Readonly<typeof initialState>;

// Reducer

export default (state: QCashUploadRowState = initialState, action): QCashUploadRowState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_QCASHUPLOADROW_LIST):
    case REQUEST(ACTION_TYPES.FETCH_QCASHUPLOADROW):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_QCASHUPLOADROW):
    case REQUEST(ACTION_TYPES.UPDATE_QCASHUPLOADROW):
    case REQUEST(ACTION_TYPES.DELETE_QCASHUPLOADROW):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_QCASHUPLOADROW_LIST):
    case FAILURE(ACTION_TYPES.FETCH_QCASHUPLOADROW):
    case FAILURE(ACTION_TYPES.CREATE_QCASHUPLOADROW):
    case FAILURE(ACTION_TYPES.UPDATE_QCASHUPLOADROW):
    case FAILURE(ACTION_TYPES.DELETE_QCASHUPLOADROW):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_QCASHUPLOADROW_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_QCASHUPLOADROW):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_QCASHUPLOADROW):
    case SUCCESS(ACTION_TYPES.UPDATE_QCASHUPLOADROW):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_QCASHUPLOADROW):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/q-cash-upload-rows';

// Actions

export const getEntities: ICrudGetAllAction<IQCashUploadRow> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_QCASHUPLOADROW_LIST,
    payload: axios.get<IQCashUploadRow>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IQCashUploadRow> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_QCASHUPLOADROW,
    payload: axios.get<IQCashUploadRow>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IQCashUploadRow> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_QCASHUPLOADROW,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<IQCashUploadRow> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_QCASHUPLOADROW,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IQCashUploadRow> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_QCASHUPLOADROW,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
