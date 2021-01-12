import { Moment } from 'moment';
import { QCashUploadRowStatus } from 'app/shared/model/enumerations/q-cash-upload-row-status.model';

export interface IQCashUploadRow {
  id?: number;
  babelUserId?: number;
  email?: string;
  phone?: number;
  amount?: number;
  status?: QCashUploadRowStatus;
  createdBy?: string;
  createdOn?: string;
  updatedBy?: string;
  updatedOn?: string;
  jobIdId?: number;
}

export const defaultValue: Readonly<IQCashUploadRow> = {};
