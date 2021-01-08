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

import { IDefaultTemplates, defaultValue } from 'app/shared/model/default-templates.model';

export const ACTION_TYPES = {
  FETCH_DEFAULTTEMPLATES_LIST: 'defaultTemplates/FETCH_DEFAULTTEMPLATES_LIST',
  FETCH_DEFAULTTEMPLATES: 'defaultTemplates/FETCH_DEFAULTTEMPLATES',
  CREATE_DEFAULTTEMPLATES: 'defaultTemplates/CREATE_DEFAULTTEMPLATES',
  UPDATE_DEFAULTTEMPLATES: 'defaultTemplates/UPDATE_DEFAULTTEMPLATES',
  DELETE_DEFAULTTEMPLATES: 'defaultTemplates/DELETE_DEFAULTTEMPLATES',
  RESET: 'defaultTemplates/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDefaultTemplates>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type DefaultTemplatesState = Readonly<typeof initialState>;

// Reducer

export default (state: DefaultTemplatesState = initialState, action): DefaultTemplatesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DEFAULTTEMPLATES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DEFAULTTEMPLATES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_DEFAULTTEMPLATES):
    case REQUEST(ACTION_TYPES.UPDATE_DEFAULTTEMPLATES):
    case REQUEST(ACTION_TYPES.DELETE_DEFAULTTEMPLATES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_DEFAULTTEMPLATES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DEFAULTTEMPLATES):
    case FAILURE(ACTION_TYPES.CREATE_DEFAULTTEMPLATES):
    case FAILURE(ACTION_TYPES.UPDATE_DEFAULTTEMPLATES):
    case FAILURE(ACTION_TYPES.DELETE_DEFAULTTEMPLATES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_DEFAULTTEMPLATES_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_DEFAULTTEMPLATES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_DEFAULTTEMPLATES):
    case SUCCESS(ACTION_TYPES.UPDATE_DEFAULTTEMPLATES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_DEFAULTTEMPLATES):
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

const apiUrl = 'api/default-templates';

// Actions

export const getEntities: ICrudGetAllAction<IDefaultTemplates> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DEFAULTTEMPLATES_LIST,
    payload: axios.get<IDefaultTemplates>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IDefaultTemplates> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DEFAULTTEMPLATES,
    payload: axios.get<IDefaultTemplates>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IDefaultTemplates> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DEFAULTTEMPLATES,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<IDefaultTemplates> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DEFAULTTEMPLATES,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDefaultTemplates> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DEFAULTTEMPLATES,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
