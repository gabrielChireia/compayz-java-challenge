import { notifications } from "@mantine/notifications"


export const errorNotification = (error) => {
  notifications.show({
    title: 'Error!',
    message: error.message,
    color: 'red',
  })
}

export const successNotification = (value) => {
  notifications.show({
    title: 'Success!',
    message: `Client ${value} saved`,
    color: 'blue',
  })
}