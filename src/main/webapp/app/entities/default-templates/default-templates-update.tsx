import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDefaultTemplatesFields } from 'app/shared/model/default-templates-fields.model';
import { getEntities as getDefaultTemplatesFields } from 'app/entities/default-templates-fields/default-templates-fields.reducer';
import { getEntity, updateEntity, createEntity, reset } from './default-templates.reducer';
import { IDefaultTemplates } from 'app/shared/model/default-templates.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDefaultTemplatesUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DefaultTemplatesUpdate = (props: IDefaultTemplatesUpdateProps) => {
  const [idsdefaultTemplatesFields, setIdsdefaultTemplatesFields] = useState([]);
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { defaultTemplatesEntity, defaultTemplatesFields, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/default-templates');
  };

  useEffect(() => {
    if (!isNew) {
      props.getEntity(props.match.params.id);
    }

    props.getDefaultTemplatesFields();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...defaultTemplatesEntity,
        ...values,
        defaultTemplatesFields: mapIdList(values.defaultTemplatesFields),
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
          <h2 id="jobsOpsToolApp.defaultTemplates.home.createOrEditLabel">
            <Translate contentKey="jobsOpsToolApp.defaultTemplates.home.createOrEditLabel">Create or edit a DefaultTemplates</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : defaultTemplatesEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="default-templates-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="default-templates-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="default-templates-name">
                  <Translate contentKey="jobsOpsToolApp.defaultTemplates.name">Name</Translate>
                </Label>
                <AvField
                  id="default-templates-name"
                  type="text"
                  name="name"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="contentStyleLabel" for="default-templates-contentStyle">
                  <Translate contentKey="jobsOpsToolApp.defaultTemplates.contentStyle">Content Style</Translate>
                </Label>
                <AvField
                  id="default-templates-contentStyle"
                  type="text"
                  name="contentStyle"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="commentsLabel" for="default-templates-comments">
                  <Translate contentKey="jobsOpsToolApp.defaultTemplates.comments">Comments</Translate>
                </Label>
                <AvField id="default-templates-comments" type="text" name="comments" />
              </AvGroup>
              <AvGroup>
                <Label id="approvedTemplateIdLabel" for="default-templates-approvedTemplateId">
                  <Translate contentKey="jobsOpsToolApp.defaultTemplates.approvedTemplateId">Approved Template Id</Translate>
                </Label>
                <AvField
                  id="default-templates-approvedTemplateId"
                  type="text"
                  name="approvedTemplateId"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="default-templates-createdBy">
                  <Translate contentKey="jobsOpsToolApp.defaultTemplates.createdBy">Created By</Translate>
                </Label>
                <AvField id="default-templates-createdBy" type="text" name="createdBy" />
              </AvGroup>
              <AvGroup>
                <Label id="createdOnLabel" for="default-templates-createdOn">
                  <Translate contentKey="jobsOpsToolApp.defaultTemplates.createdOn">Created On</Translate>
                </Label>
                <AvField id="default-templates-createdOn" type="date" className="form-control" name="createdOn" />
              </AvGroup>
              <AvGroup>
                <Label id="updatedByLabel" for="default-templates-updatedBy">
                  <Translate contentKey="jobsOpsToolApp.defaultTemplates.updatedBy">Updated By</Translate>
                </Label>
                <AvField id="default-templates-updatedBy" type="text" name="updatedBy" />
              </AvGroup>
              <AvGroup>
                <Label id="updatedOnLabel" for="default-templates-updatedOn">
                  <Translate contentKey="jobsOpsToolApp.defaultTemplates.updatedOn">Updated On</Translate>
                </Label>
                <AvField id="default-templates-updatedOn" type="date" className="form-control" name="updatedOn" />
              </AvGroup>
              <AvGroup>
                <Label for="default-templates-defaultTemplatesFields">
                  <Translate contentKey="jobsOpsToolApp.defaultTemplates.defaultTemplatesFields">Default Templates Fields</Translate>
                </Label>
                <AvInput
                  id="default-templates-defaultTemplatesFields"
                  type="select"
                  multiple
                  className="form-control"
                  name="defaultTemplatesFields"
                  value={defaultTemplatesEntity.defaultTemplatesFields && defaultTemplatesEntity.defaultTemplatesFields.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {defaultTemplatesFields
                    ? defaultTemplatesFields.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/default-templates" replace color="info">
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
  defaultTemplatesFields: storeState.defaultTemplatesFields.entities,
  defaultTemplatesEntity: storeState.defaultTemplates.entity,
  loading: storeState.defaultTemplates.loading,
  updating: storeState.defaultTemplates.updating,
  updateSuccess: storeState.defaultTemplates.updateSuccess,
});

const mapDispatchToProps = {
  getDefaultTemplatesFields,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DefaultTemplatesUpdate);
