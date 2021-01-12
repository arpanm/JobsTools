import { Moment } from 'moment';
import { IQCashUploadRow } from 'app/shared/model/q-cash-upload-row.model';
import { UploadType } from 'app/shared/model/enumerations/upload-type.model';
import { UploadStatus } from 'app/shared/model/enumerations/upload-status.model';

export interface IUploadJob {
  id?: number;
  type?: UploadType;
  url?: string;
  status?: UploadStatus;
  createdBy?: string;
  createdOn?: string;
  updatedBy?: string;
  updatedOn?: string;
  qCashUploadRows?: IQCashUploadRow[];
}

export const defaultValue: Readonly<IUploadJob> = {};
