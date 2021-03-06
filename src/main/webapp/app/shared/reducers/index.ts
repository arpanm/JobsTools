import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import defaultTemplates, {
  DefaultTemplatesState
} from 'app/entities/default-templates/default-templates.reducer';
// prettier-ignore
import defaultTemplatesFields, {
  DefaultTemplatesFieldsState
} from 'app/entities/default-templates-fields/default-templates-fields.reducer';
// prettier-ignore
import uploadJob, {
  UploadJobState
} from 'app/entities/upload-job/upload-job.reducer';
// prettier-ignore
import qCashUploadRow, {
  QCashUploadRowState
} from 'app/entities/q-cash-upload-row/q-cash-upload-row.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly defaultTemplates: DefaultTemplatesState;
  readonly defaultTemplatesFields: DefaultTemplatesFieldsState;
  readonly uploadJob: UploadJobState;
  readonly qCashUploadRow: QCashUploadRowState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  defaultTemplates,
  defaultTemplatesFields,
  uploadJob,
  qCashUploadRow,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
});

export default rootReducer;
