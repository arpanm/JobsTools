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

import { IUploadJob, defaultValue } from 'app/shared/model/upload-job.model';

export const ACTION_TYPES = {
  FETCH_UPLOADJOB_LIST: 'uploadJob/FETCH_UPLOADJOB_LIST',
  FETCH_UPLOADJOB: 'uploadJob/FETCH_UPLOADJOB',
  CREATE_UPLOADJOB: 'uploadJob/CREATE_UPLOADJOB',
  UPDATE_UPLOADJOB: 'uploadJob/UPDATE_UPLOADJOB',
  DELETE_UPLOADJOB: 'uploadJob/DELETE_UPLOADJOB',
  RESET: 'uploadJob/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IUploadJob>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type UploadJobState = Readonly<typeof initialState>;

// Reducer

export default (state: UploadJobState = initialState, action): UploadJobState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_UPLOADJOB_LIST):
    case REQUEST(ACTION_TYPES.FETCH_UPLOADJOB):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_UPLOADJOB):
    case REQUEST(ACTION_TYPES.UPDATE_UPLOADJOB):
    case REQUEST(ACTION_TYPES.DELETE_UPLOADJOB):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_UPLOADJOB_LIST):
    case FAILURE(ACTION_TYPES.FETCH_UPLOADJOB):
    case FAILURE(ACTION_TYPES.CREATE_UPLOADJOB):
    case FAILURE(ACTION_TYPES.UPDATE_UPLOADJOB):
    case FAILURE(ACTION_TYPES.DELETE_UPLOADJOB):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_UPLOADJOB_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_UPLOADJOB):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_UPLOADJOB):
    case SUCCESS(ACTION_TYPES.UPDATE_UPLOADJOB):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_UPLOADJOB):
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

const apiUrl = 'api/upload-jobs';

// Actions

export const getEntities: ICrudGetAllAction<IUploadJob> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_UPLOADJOB_LIST,
    payload: axios.get<IUploadJob>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IUploadJob> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_UPLOADJOB,
    payload: axios.get<IUploadJob>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IUploadJob> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_UPLOADJOB,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<IUploadJob> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_UPLOADJOB,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IUploadJob> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_UPLOADJOB,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
