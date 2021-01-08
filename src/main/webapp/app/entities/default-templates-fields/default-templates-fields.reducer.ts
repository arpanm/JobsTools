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

import { IDefaultTemplatesFields, defaultValue } from 'app/shared/model/default-templates-fields.model';

export const ACTION_TYPES = {
  FETCH_DEFAULTTEMPLATESFIELDS_LIST: 'defaultTemplatesFields/FETCH_DEFAULTTEMPLATESFIELDS_LIST',
  FETCH_DEFAULTTEMPLATESFIELDS: 'defaultTemplatesFields/FETCH_DEFAULTTEMPLATESFIELDS',
  CREATE_DEFAULTTEMPLATESFIELDS: 'defaultTemplatesFields/CREATE_DEFAULTTEMPLATESFIELDS',
  UPDATE_DEFAULTTEMPLATESFIELDS: 'defaultTemplatesFields/UPDATE_DEFAULTTEMPLATESFIELDS',
  DELETE_DEFAULTTEMPLATESFIELDS: 'defaultTemplatesFields/DELETE_DEFAULTTEMPLATESFIELDS',
  RESET: 'defaultTemplatesFields/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDefaultTemplatesFields>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type DefaultTemplatesFieldsState = Readonly<typeof initialState>;

// Reducer

export default (state: DefaultTemplatesFieldsState = initialState, action): DefaultTemplatesFieldsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DEFAULTTEMPLATESFIELDS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DEFAULTTEMPLATESFIELDS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_DEFAULTTEMPLATESFIELDS):
    case REQUEST(ACTION_TYPES.UPDATE_DEFAULTTEMPLATESFIELDS):
    case REQUEST(ACTION_TYPES.DELETE_DEFAULTTEMPLATESFIELDS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_DEFAULTTEMPLATESFIELDS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DEFAULTTEMPLATESFIELDS):
    case FAILURE(ACTION_TYPES.CREATE_DEFAULTTEMPLATESFIELDS):
    case FAILURE(ACTION_TYPES.UPDATE_DEFAULTTEMPLATESFIELDS):
    case FAILURE(ACTION_TYPES.DELETE_DEFAULTTEMPLATESFIELDS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_DEFAULTTEMPLATESFIELDS_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_DEFAULTTEMPLATESFIELDS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_DEFAULTTEMPLATESFIELDS):
    case SUCCESS(ACTION_TYPES.UPDATE_DEFAULTTEMPLATESFIELDS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_DEFAULTTEMPLATESFIELDS):
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

const apiUrl = 'api/default-templates-fields';

// Actions

export const getEntities: ICrudGetAllAction<IDefaultTemplatesFields> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DEFAULTTEMPLATESFIELDS_LIST,
    payload: axios.get<IDefaultTemplatesFields>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IDefaultTemplatesFields> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DEFAULTTEMPLATESFIELDS,
    payload: axios.get<IDefaultTemplatesFields>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IDefaultTemplatesFields> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DEFAULTTEMPLATESFIELDS,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<IDefaultTemplatesFields> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DEFAULTTEMPLATESFIELDS,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDefaultTemplatesFields> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DEFAULTTEMPLATESFIELDS,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
