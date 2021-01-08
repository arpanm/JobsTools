import { IDefaultTemplates } from 'app/shared/model/default-templates.model';
import { DefaultFieldType } from 'app/shared/model/enumerations/default-field-type.model';

export interface IDefaultTemplatesFields {
  id?: number;
  name?: string;
  type?: DefaultFieldType;
  displayName?: string;
  defaultTemplates?: IDefaultTemplates[];
}

export const defaultValue: Readonly<IDefaultTemplatesFields> = {};
