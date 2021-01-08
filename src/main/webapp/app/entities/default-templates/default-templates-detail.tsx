import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './default-templates.reducer';
import { IDefaultTemplates } from 'app/shared/model/default-templates.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDefaultTemplatesDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DefaultTemplatesDetail = (props: IDefaultTemplatesDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { defaultTemplatesEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jobsOpsToolApp.defaultTemplates.detail.title">DefaultTemplates</Translate> [
          <b>{defaultTemplatesEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="jobsOpsToolApp.defaultTemplates.name">Name</Translate>
            </span>
          </dt>
          <dd>{defaultTemplatesEntity.name}</dd>
          <dt>
            <span id="contentStyle">
              <Translate contentKey="jobsOpsToolApp.defaultTemplates.contentStyle">Content Style</Translate>
            </span>
          </dt>
          <dd>{defaultTemplatesEntity.contentStyle}</dd>
          <dt>
            <span id="comments">
              <Translate contentKey="jobsOpsToolApp.defaultTemplates.comments">Comments</Translate>
            </span>
          </dt>
          <dd>{defaultTemplatesEntity.comments}</dd>
          <dt>
            <span id="approvedTemplateId">
              <Translate contentKey="jobsOpsToolApp.defaultTemplates.approvedTemplateId">Approved Template Id</Translate>
            </span>
          </dt>
          <dd>{defaultTemplatesEntity.approvedTemplateId}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="jobsOpsToolApp.defaultTemplates.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{defaultTemplatesEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="jobsOpsToolApp.defaultTemplates.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {defaultTemplatesEntity.createdOn ? (
              <TextFormat value={defaultTemplatesEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="jobsOpsToolApp.defaultTemplates.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{defaultTemplatesEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="jobsOpsToolApp.defaultTemplates.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {defaultTemplatesEntity.updatedOn ? (
              <TextFormat value={defaultTemplatesEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="jobsOpsToolApp.defaultTemplates.defaultTemplatesFields">Default Templates Fields</Translate>
          </dt>
          <dd>
            {defaultTemplatesEntity.defaultTemplatesFields
              ? defaultTemplatesEntity.defaultTemplatesFields.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {defaultTemplatesEntity.defaultTemplatesFields && i === defaultTemplatesEntity.defaultTemplatesFields.length - 1
                      ? ''
                      : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/default-templates" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/default-templates/${defaultTemplatesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ defaultTemplates }: IRootState) => ({
  defaultTemplatesEntity: defaultTemplates.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DefaultTemplatesDetail);
