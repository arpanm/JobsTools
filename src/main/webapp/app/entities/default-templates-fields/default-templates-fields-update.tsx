import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDefaultTemplates } from 'app/shared/model/default-templates.model';
import { getEntities as getDefaultTemplates } from 'app/entities/default-templates/default-templates.reducer';
import { getEntity, updateEntity, createEntity, reset } from './default-templates-fields.reducer';
import { IDefaultTemplatesFields } from 'app/shared/model/default-templates-fields.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDefaultTemplatesFieldsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DefaultTemplatesFieldsUpdate = (props: IDefaultTemplatesFieldsUpdateProps) => {
  const [defaultTemplatesId, setDefaultTemplatesId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { defaultTemplatesFieldsEntity, defaultTemplates, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/default-templates-fields');
  };

  useEffect(() => {
    if (!isNew) {
      props.getEntity(props.match.params.id);
    }

    props.getDefaultTemplates();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...defaultTemplatesFieldsEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="jobsOpsToolApp.defaultTemplatesFields.home.createOrEditLabel">
            <Translate contentKey="jobsOpsToolApp.defaultTemplatesFields.home.createOrEditLabel">
              Create or edit a DefaultTemplatesFields
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : defaultTemplatesFieldsEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="default-templates-fields-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="default-templates-fields-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="default-templates-fields-name">
                  <Translate contentKey="jobsOpsToolApp.defaultTemplatesFields.name">Name</Translate>
                </Label>
                <AvField
                  id="default-templates-fields-name"
                  type="text"
                  name="name"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="default-templates-fields-type">
                  <Translate contentKey="jobsOpsToolApp.defaultTemplatesFields.type">Type</Translate>
                </Label>
                <AvInput
                  id="default-templates-fields-type"
                  type="select"
                  className="form-control"
                  name="type"
                  value={(!isNew && defaultTemplatesFieldsEntity.type) || 'STR'}
                >
                  <option value="STR">{translate('jobsOpsToolApp.DefaultFieldType.STR')}</option>
                  <option value="NUM">{translate('jobsOpsToolApp.DefaultFieldType.NUM')}</option>
                  <option value="DROP_DOWN">{translate('jobsOpsToolApp.DefaultFieldType.DROP_DOWN')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="displayNameLabel" for="default-templates-fields-displayName">
                  <Translate contentKey="jobsOpsToolApp.defaultTemplatesFields.displayName">Display Name</Translate>
                </Label>
                <AvField id="default-templates-fields-displayName" type="text" name="displayName" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/default-templates-fields" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  defaultTemplates: storeState.defaultTemplates.entities,
  defaultTemplatesFieldsEntity: storeState.defaultTemplatesFields.entity,
  loading: storeState.defaultTemplatesFields.loading,
  updating: storeState.defaultTemplatesFields.updating,
  updateSuccess: storeState.defaultTemplatesFields.updateSuccess,
});

const mapDispatchToProps = {
  getDefaultTemplates,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DefaultTemplatesFieldsUpdate);
