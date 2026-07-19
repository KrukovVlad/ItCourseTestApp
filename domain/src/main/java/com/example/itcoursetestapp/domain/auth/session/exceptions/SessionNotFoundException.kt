package com.example.itcoursetestapp.domain.auth.session.exceptions

import com.example.itcoursetestapp.domain.core.exceptions.DomainException

class SessionNotFoundException : DomainException("User session not found")
