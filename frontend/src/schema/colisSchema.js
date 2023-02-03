import * as Yup from 'yup';

export const colisSchema = Yup.object().shape({
    description:Yup.string().required("The description is required"),
    clientCNI:Yup.string(),
    clientName:Yup.string(),
    recieverName:Yup.string().required("Reciever name is require"),
    recieverPhone:Yup.string().required("Reciever phone number require"),
    fromTown:Yup.string().required("Require"),
    toTown:Yup.string().required("Require")
})