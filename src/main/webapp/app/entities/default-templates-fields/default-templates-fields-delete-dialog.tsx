import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IDefaultTemplatesFields } from 'app/shared/model/default-templates-fields.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './default-templates-fields.reducer';

export interface IDefaultTemplatesFieldsDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DefaultTemplatesFieldsDeleteDialog = (props: IDefaultTemplatesFieldsDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/default-templates-fields');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.defaultTemplatesFieldsEntity.id);
  };

  const { defaultTemplatesFieldsEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="jobsOpsToolApp.defaultTemplatesFields.delete.question">
        <Translate contentKey="jobsOpsToolApp.defaultTemplatesFields.delete.question" interpolate={{ id: defaultTemplatesFieldsEntity.id }}>
          Are you sure you want to delete this DefaultTemplatesFields?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-defaultTemplatesFields" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ defaultTemplatesFields }: IRootState) => ({
  defaultTemplatesFieldsEntity: defaultTemplatesFields.entity,
  updateSuccess: defaultTemplatesFields.updateSuccess,
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DefaultTemplatesFieldsDeleteDialog);
